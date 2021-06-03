package com.yat.wechatorderingsystem.service;

import com.yat.wechatorderingsystem.dto.OrderDTO;

public interface BuyerService {

    // 查询订单
    OrderDTO selectOrderById(String orderId,String openid);

    // 取消订单
    OrderDTO cancelOrderById(String orderId,String openid);
}
