package com.yat.wechatorderingsystem.controller;

import com.lly835.bestpay.model.PayResponse;
import com.yat.wechatorderingsystem.dto.OrderDTO;
import com.yat.wechatorderingsystem.enums.ResultEnum;
import com.yat.wechatorderingsystem.exception.SellException;
import com.yat.wechatorderingsystem.service.Impl.OrderServiceImpl;
import com.yat.wechatorderingsystem.service.Impl.PayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private PayServiceImpl payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String,Object> map){

        // 1.查询订单
        OrderDTO orderDTO = orderService.selectById(orderId);
        if (orderDTO==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXISTS);
        }

        // 2.发起支付
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse",payResponse);
        String decode = null;
        try {
            decode = URLDecoder.decode(returnUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("【支付订单】解析返回地址错误，returnUrl={}",returnUrl);
            e.printStackTrace();
        }
        map.put("returnUrl",decode);
        return new ModelAndView("pay/create",map);
    }

    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){
        payService.notify(notifyData);

        // 返回支付成功通知响应
        return new ModelAndView("pay/success");
    }
}
