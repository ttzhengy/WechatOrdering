package com.yat.wechatorderingsystem.dao;

import com.yat.wechatorderingsystem.entity.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.transaction.AfterTransaction;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductCategoryDaoTest {

    @Autowired
    ProductCategoryDao productCategoryDao;

    @Test
    public void selectByIdTest(){
        productCategoryDao.insertProductCategory(new ProductCategory(1,"aaa","22"));
        ProductCategory productCategory = productCategoryDao.selectById(1);
        System.out.println(productCategory.toString());
    }
}