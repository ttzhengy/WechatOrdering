package com.yat.wechatorderingsystem.dao;

import com.yat.wechatorderingsystem.entity.ProductInfo;
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
class ProductInfoDaoTest {

    @Autowired
    ProductInfoDao productInfoDao;

    @Test
    void selectByIdTest() {
        ProductInfo productInfo = productInfoDao.selectById("1001");
        Assertions.assertNotNull(productInfo);
    }

    @Test
    void selectByCategoryTypeTest() {
        List<ProductInfo> productInfos = productInfoDao.selectByCategoryType(1);
        Assertions.assertNotEquals(0,productInfos.size());
    }

    @Test
    void selectByProductStatusTest() {
        List<ProductInfo> productInfos = productInfoDao.selectByProductStatus(0);
        Assertions.assertNotEquals(0,productInfos.size());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    void insertProductInfoTest() {
        ProductInfo product1 = new ProductInfo("1001", "森蚺",
                new BigDecimal(648), 1, "很涩",
                "xxx/xxx.jpg", null, 1);
        boolean flag = productInfoDao.insertProductInfo(product1);
        Assertions.assertTrue(flag,"insert执行失败");
    }

    @Test
    @Transactional
    @Rollback(value = true)
    void updateProductInfoTest() {
        ProductInfo skadi = productInfoDao.selectById("1002");
        skadi.setCategoryType(1);
        boolean flag = productInfoDao.updateProductInfo(skadi);
        Assertions.assertTrue(flag,"修改失败");
    }
}