package com.yat.wechatorderingsystem.dao;

import com.yat.wechatorderingsystem.entity.ProductCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
class ProductCategoryDaoTest {

    @Autowired
    ProductCategoryDao productCategoryDao;

    @Test
    @Transactional  //这两个注解负责事务回滚
    @Rollback(value = false)
    public void selectByIdTest(){
        // productCategoryDao.insertProductCategory(new ProductCategory(1,"aaa",22));
        ProductCategory productCategory = productCategoryDao.selectById(1);
        System.out.println(productCategory.toString());
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void selectAllTest(){
        List<ProductCategory> productCategories = productCategoryDao.selectAll();
        productCategories.forEach(System.out::println);
        Assertions.assertNotEquals(0,productCategories.size());
    }

    @Test
    @Transactional  //这两个注解负责事务回滚
    @Rollback(value = true)
    public void insertTest(){
        ProductCategory  productCategory = new ProductCategory(null,"zzz",1212);
        productCategoryDao.insertProductCategory(productCategory);
        System.out.println(productCategory.getCategoryId());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void updateByIdTest(){
        ProductCategory productCategory = productCategoryDao.selectById(2);
        productCategory.setCategoryType(45);
        productCategoryDao.updateProductCategory(productCategory);
        productCategory = productCategoryDao.selectById(2);
        Assertions.assertEquals(productCategory.getCategoryType(),45);
    }

    @Test
    public void selectByType(){
        List<Integer> types = Arrays.asList(33,44);
        List<ProductCategory> productCategories = productCategoryDao.selectByType(types);
        Assertions.assertEquals(2,productCategories.size());
    }
}