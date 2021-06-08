package com.yat.wechatorderingsystem.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.yat.wechatorderingsystem.dto.OrderDTO;

public interface PayService {

    // 创建预支付订单
   PayResponse create(OrderDTO orderDTO);

    // 接受微信异步通知
   PayResponse notify(String notifyData);

   // 退款
   RefundResponse refund(OrderDTO orderDTO);
}
