package com.yat.wechatorderingsystem.service.Impl;

import com.yat.wechatorderingsystem.dto.OrderDTO;
import com.yat.wechatorderingsystem.entity.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class OrderServiceImplTest {

    @Autowired
    OrderServiceImpl orderService;

    @Test
    @Transactional
    @Rollback(value = true)
    void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("氩天");
        orderDTO.setBuyerPhone("13111331331");
        orderDTO.setBuyerAddress("gdut");
        orderDTO.setBuyerOpenid("123456789");

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("1005");
        o1.setProductQuantity(10);
        orderDetailList.add(o1);
        // OrderDetail o2 = new OrderDetail();
        // o2.setProductId("1002");
        // o2.setProductQuantity(20);
        // orderDetailList.add(o2);
        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        log.info("返回DTO：{}",result);
    }

    @Test
    void selectById() {
        OrderDTO result = orderService.selectById("1622644082103870068");
        log.info("返回DTO：{}",result);
        Assertions.assertNotNull(result);
    }

    @Test
    void selectByOpenId() {
        List<OrderDTO> orderDTOList = orderService.selectByOpenId("114514",1,1);
        log.info("返回DTO：{}",orderDTOList);
        Assertions.assertEquals(1,orderDTOList.size());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    void cancel() {
        OrderDTO orderDTO = orderService.selectById("10001");
        OrderDTO result = orderService.cancel(orderDTO);
        Assertions.assertEquals(2,result.getOrderStatus());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    void paid() {
        OrderDTO orderDTO = orderService.selectById("10001");
        OrderDTO result = orderService.paid(orderDTO);
        Assertions.assertEquals(1,result.getPayStatus());
    }

    @Test
    @Transactional
    void finish() {
        OrderDTO orderDTO = orderService.selectById("10001");
        // orderDTO.setOrderId("10000");
        OrderDTO result = orderService.finish(orderDTO);
        Assertions.assertEquals(1,result.getOrderStatus());
    }
}