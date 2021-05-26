package com.yat.wechatorderingsystem.dao;

import com.yat.wechatorderingsystem.entity.OrderDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderDetailDaoTest {

    @Autowired
    OrderDetailDao orderDetailDao;

    @Test
    void selectTest(){
        OrderDetail orderDetail = orderDetailDao.selectById("1000101");
        Assertions.assertEquals("森蚺",orderDetail.getProductName());
    }

    @Test
    void selectByOrderIdTest(){
        List<OrderDetail> orderDetails = orderDetailDao.selectByOrderId("10001");
        Assertions.assertNotEquals(0,orderDetails.size());
    }

    @Test
    void selectByProductIdTest(){
        List<OrderDetail> orderDetails = orderDetailDao.selectByProductId("1001");
        Assertions.assertNotEquals(0,orderDetails.size());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    void insertTest(){
        OrderDetail detail = new OrderDetail("1000101", "10001",
                "1001", "森蚺", new BigDecimal(648),
                5, "xxx/xxx.jpg");
        boolean flag = orderDetailDao.insertOrderDetail(detail);
        Assertions.assertTrue(flag,"插入失败");
    }

    @Test
    @Transactional
    @Rollback(value = true)
    void updateTest(){
        OrderDetail orderDetail = orderDetailDao.selectById("1000101");
        orderDetail.setProductQuantity(10);
        boolean flag = orderDetailDao.updateOrderDetail(orderDetail);
        Assertions.assertTrue(flag,"修改失败");
    }
}