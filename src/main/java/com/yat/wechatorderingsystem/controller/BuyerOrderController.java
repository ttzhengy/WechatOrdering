package com.yat.wechatorderingsystem.controller;

import com.yat.wechatorderingsystem.Util.ResultVOUtil;
import com.yat.wechatorderingsystem.VO.ResultVO;
import com.yat.wechatorderingsystem.converter.OrderForm2OrderDTOConverter;
import com.yat.wechatorderingsystem.dto.OrderDTO;
import com.yat.wechatorderingsystem.enums.ResultEnum;
import com.yat.wechatorderingsystem.exception.SellException;
import com.yat.wechatorderingsystem.form.OrderForm;
import com.yat.wechatorderingsystem.service.BuyerService;
import com.yat.wechatorderingsystem.service.Impl.BuyerServiceImpl;
import com.yat.wechatorderingsystem.service.Impl.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.BindingType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    BuyerServiceImpl buyerService;

    // 创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){

        // 判断表单是否完整
        if (bindingResult.hasErrors()){
            log.error("【创建订单】表单参数不正确，orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        // 创建订单
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO result = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",result.getOrderId());

        return ResultVOUtil.success(map);
    }

    // 订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam("page") Integer pageNum,
                                         @RequestParam("size") Integer size){
        if (!StringUtils.hasLength(openid)){
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        List<OrderDTO> orderDTOList = orderService.selectByOpenId(openid, pageNum, size);
        return ResultVOUtil.success(orderDTOList);
    }

    // 订单细节
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderid") String orderid){
        if (!StringUtils.hasLength(openid)){
            log.error("【查询订单细节】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        OrderDTO orderDTO = buyerService.selectOrderById(openid, orderid);

        return ResultVOUtil.success(orderDTO);
    }

    // 取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderid") String orderid){
        if (!StringUtils.hasLength(openid)){
            log.error("【取消订单】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        OrderDTO orderDTO = buyerService.cancelOrderById(openid, orderid);

        return ResultVOUtil.success();
    }
}
