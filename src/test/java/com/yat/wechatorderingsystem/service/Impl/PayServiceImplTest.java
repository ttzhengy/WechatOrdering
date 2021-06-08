package com.yat.wechatorderingsystem.service.Impl;

import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.yat.wechatorderingsystem.dto.OrderDTO;
import com.yat.wechatorderingsystem.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class PayServiceImplTest {

    @Autowired
    PayService payService;

    @Autowired
    OrderServiceImpl orderService;

    @Test
    void create() {
        OrderDTO orderDTO = orderService.selectById("1622644082103870068");
        payService.create(orderDTO);

        // 返回
        // 【微信支付】response：{
        //     "appId": "wxd898fcb01713c658",
        //     "timeStamp": "1623048655",
        //     "nonceStr": "kcTwueQnd1QT0rRm",
        //     "packAge": "prepay_id\u003dwx07145055500940fa77500d9c568f080000",
        //     "signType": "MD5",
        //     "paySign": "0E7910F106D50671FAB46B93F6D1A20A"
        // }
    }

    @Test
    void refund(){
        OrderDTO orderDTO = orderService.selectById("1622644082103870068");
        payService.refund(orderDTO);
    }
}