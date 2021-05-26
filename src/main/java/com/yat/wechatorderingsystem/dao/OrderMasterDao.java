package com.yat.wechatorderingsystem.dao;

import com.yat.wechatorderingsystem.entity.OrderMaster;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMasterDao {

    OrderMaster selectById(String id);

    List<OrderMaster> selectByBuyerOpenId(String buyerOpenId);

    boolean insertOrderMaster(OrderMaster orderMaster);

    boolean updateOrderMaster(OrderMaster orderMaster);
}
