package com.yat.wechatorderingsystem.service;

import com.yat.wechatorderingsystem.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    // 创建订单
    OrderDTO create(OrderDTO orderDTO);

    // 查询订单及细节
    OrderDTO selectById(String orderId);

    // 根据openid查询订单
    List<OrderDTO> selectByOpenId(String openId, Integer pageNum, Integer size);

    // 取消订单
    OrderDTO cancel(OrderDTO orderDTO);

    // 支付订单
    OrderDTO paid(OrderDTO orderDTO);

    // 完成订单
    OrderDTO finish(OrderDTO orderDTO);
}
