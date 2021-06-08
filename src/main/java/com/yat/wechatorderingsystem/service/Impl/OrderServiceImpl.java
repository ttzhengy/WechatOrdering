package com.yat.wechatorderingsystem.service.Impl;

import com.github.pagehelper.PageHelper;
import com.yat.wechatorderingsystem.Util.KeyUtil;
import com.yat.wechatorderingsystem.converter.OrderMaster2OrderDTOConverter;
import com.yat.wechatorderingsystem.dao.OrderDetailDao;
import com.yat.wechatorderingsystem.dao.OrderMasterDao;
import com.yat.wechatorderingsystem.dto.CartDTO;
import com.yat.wechatorderingsystem.dto.OrderDTO;
import com.yat.wechatorderingsystem.entity.OrderDetail;
import com.yat.wechatorderingsystem.entity.OrderMaster;
import com.yat.wechatorderingsystem.entity.ProductInfo;
import com.yat.wechatorderingsystem.enums.ResultEnum;
import com.yat.wechatorderingsystem.exception.SellException;
import com.yat.wechatorderingsystem.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.annotation.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Autowired
    private PayServiceImpl payService;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        // 查询商品，判断是否存在
        String orderID = KeyUtil.getUniqueKey();
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        BigDecimal totalAmount = new BigDecimal(0);
        for (OrderDetail orderDetail : orderDetailList) {
            ProductInfo productInfo = productService.selectById(orderDetail.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

        // 计算总价
            totalAmount = totalAmount.add(productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity())));

        // 写入数据库
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setOrderId(orderID);
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetailDao.insertOrderDetail(orderDetail);
        }
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderID);
        orderDTO.setOrderAmount(totalAmount);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderStatus(0);
        orderMaster.setPayStatus(0);
        orderMasterDao.insertOrderMaster(orderMaster);

        // 扣除库存，判断库存是否足够
        List<CartDTO> cartDTOList = orderDetailList.stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO selectById(String orderId) {
        // 查询、判断主表
        OrderMaster orderMaster = orderMasterDao.selectById(orderId);
        if (orderMaster==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXISTS);
        }

        // 查询、判断订单细节
        List<OrderDetail> orderDetailList = orderDetailDao.selectByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXISTS);
        }

        // 转换对象
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public List<OrderDTO> selectByOpenId(String openId,Integer pageNum,Integer size) {
        PageHelper.startPage(pageNum,size);
        List<OrderMaster> orderMasters = orderMasterDao.selectByBuyerOpenId(openId);
        return OrderMaster2OrderDTOConverter.convert(orderMasters);
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        // 查询订单，判断状态
        if (orderDTO.getOrderStatus()!=0){
            log.error("【取消订单】订单状态不正确，orderId={}，orderStatus={}",
                    orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 修改订单状态
        // OrderMaster orderMaster = orderMasterDao.selectById(orderDTO.getOrderId());
        // Integer payStatus = orderMaster.getPayStatus();
        // orderMaster.setOrderStatus(2);
        // orderDTO.setOrderStatus(2);

        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(2);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        boolean flag = orderMasterDao.updateOrderMaster(orderMaster);
        if (!flag){
            log.error("【取消订单】更新失败，orderId={}", orderMaster.getOrderId());
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        // 返回库存
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        if (CollectionUtils.isEmpty(orderDetailList)){
            log.error("【取消订单】订单详情为空，orderId={}", orderMaster.getOrderId());
            throw new SellException(ResultEnum.ORDERDETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDetailList.stream()
                .map(e->new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);

        // 如果已支付，退款
        if (orderDTO.getPayStatus()==1){
            payService.refund(orderDTO);
        }

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        // 判断订单状态
        if (orderDTO.getOrderStatus()!=0){
            log.error("【支付订单】订单状态不正确，orderId={}", orderDTO.getOrderId());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        // // 判断支付状态
        if (orderDTO.getPayStatus()!=0){
            log.error("【支付订单】订单状态不正确，orderId={}", orderDTO.getOrderId());
            throw new SellException(ResultEnum.ORDER_PAID_STATUS_ERROR);
        }
        // 修改支付状态

        orderDTO.setPayStatus(1);
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        boolean flag = orderMasterDao.updateOrderMaster(orderMaster);
        if (!flag){
            log.error("【支付订单】订单更新失败，orderId={}", orderDTO.getOrderId());
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        // 判断订单状态
        if (orderDTO.getOrderStatus()!=0){
            log.error("【完成订单】订单状态不正确，orderId={}", orderDTO.getOrderId());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 修改订单状态
        orderDTO.setOrderStatus(1);
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        boolean flag = orderMasterDao.updateOrderMaster(orderMaster);
        if (!flag){
            log.error("【完成订单】订单更新失败，orderId={}", orderDTO.getOrderId());
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}
