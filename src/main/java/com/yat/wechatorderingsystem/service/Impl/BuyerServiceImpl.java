package com.yat.wechatorderingsystem.service.Impl;

import com.yat.wechatorderingsystem.dto.OrderDTO;
import com.yat.wechatorderingsystem.enums.ResultEnum;
import com.yat.wechatorderingsystem.exception.SellException;
import com.yat.wechatorderingsystem.service.BuyerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    OrderServiceImpl orderService;

    @Override
    public OrderDTO selectOrderById(String orderId, String openid) {
       return checkOrderOwner(orderId,openid);
    }

    @Override
    public OrderDTO cancelOrderById(String orderId, String openid) {
        OrderDTO orderDTO = checkOrderOwner(orderId, openid);
        if (orderDTO==null){
            log.error("【取消订单】查不到待取消订单，openid={}",openid);
            throw new SellException(ResultEnum.ORDER_NOT_EXISTS);
        }
        return orderService.cancel(orderDTO);
    }

    public OrderDTO checkOrderOwner(String orderId, String openid){
        OrderDTO orderDTO = orderService.selectById(orderId);
        // 判断订单是否存在
        if (orderDTO==null){
            return null;
        }

        // 判断订单与openid是否一致
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【查询订单】订单的openid不一致，openid={}，orderDTO={}",openid,orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
