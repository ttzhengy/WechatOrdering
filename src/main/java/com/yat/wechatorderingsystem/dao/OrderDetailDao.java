package com.yat.wechatorderingsystem.dao;

import com.yat.wechatorderingsystem.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailDao {

    OrderDetail selectById(String id);

    List<OrderDetail> selectByOrderId(String orderId);

    List<OrderDetail> selectByProductId(String productId);

    boolean insertOrderDetail(OrderDetail orderDetail);

    boolean updateOrderDetail(OrderDetail orderDetail);
}
