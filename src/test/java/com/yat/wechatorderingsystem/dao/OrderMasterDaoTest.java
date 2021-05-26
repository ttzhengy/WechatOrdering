package com.yat.wechatorderingsystem.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yat.wechatorderingsystem.entity.OrderMaster;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
class OrderMasterDaoTest {

    @Autowired
    OrderMasterDao orderMasterDao;

    private final String openid = "114514";

    @Test
    void selectByIdTest() {
        OrderMaster orderMaster = orderMasterDao.selectById("10001");
        Assertions.assertEquals("114514",orderMaster.getBuyerOpenid());
    }

    @Test
    void selectByBuyerOpenIdAndPageTest(){
        Page<Object> objects = PageHelper.startPage(1, 1);
        List<OrderMaster> orderMasters = orderMasterDao.selectByBuyerOpenId(openid);
        Assertions.assertNotEquals(0,orderMasters.size());
    }

    @Test
    void PageTest(){
        PageHelper.startPage(1, 5);
        List<OrderMaster> orderMasters = orderMasterDao.selectByBuyerOpenId(openid);
        PageInfo<OrderMaster> pageInfo = new PageInfo<>(orderMasters);
        log.info("还有下一页？：{}",pageInfo.isHasNextPage());
        Assertions.assertNotEquals(0,pageInfo.getTotal());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    void insertOrderMasterTest() {
        OrderMaster orderMaster = new OrderMaster("20001", "yat", "13111331331",
                "foshan", "110100", new BigDecimal(128),
                0, 0);
        boolean flag = orderMasterDao.insertOrderMaster(orderMaster);
        Assertions.assertTrue(flag,"插入失败");
    }

    @Test
    @Transactional
    @Rollback(value = true)
    void updateOrderMasterTest() {
        OrderMaster orderMaster = orderMasterDao.selectById("10001");
        orderMaster.setBuyerAddress("foshan");
        boolean flag = orderMasterDao.updateOrderMaster(orderMaster);
        Assertions.assertTrue(flag,"更改失败");
    }
}