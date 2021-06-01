package com.yat.wechatorderingsystem.service.impl;

import com.yat.wechatorderingsystem.dao.ProductCategoryDao;
import com.yat.wechatorderingsystem.entity.ProductCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    void selectById() {
        ProductCategory productCategory = categoryService.selectById(1);
        Assertions.assertEquals(33,productCategory.getCategoryType());
    }

    @Test
    void selectAll() {
        List<ProductCategory> productCategories = categoryService.selectAll();
        Assertions.assertNotEquals(0,productCategories.size());
    }

    @Test
    void selectByTypeIn() {
        categoryService.selectByTypeIn(Arrays.asList(1,2));
    }

    @Test
    @Transactional
    void insert() {
        ProductCategory productCategory = new ProductCategory(null,"ddd", 66);
        categoryService.insert(productCategory);
        Assertions.assertNotNull(productCategory.getCategoryId());
    }

    @Test
    @Transactional
    void update() {
        ProductCategory productCategory = categoryService.selectById(3);
        productCategory.setCategoryType(66);
        categoryService.update(productCategory);
        ProductCategory productCategory2 = categoryService.selectById(3);
        Assertions.assertEquals(66,productCategory2.getCategoryType());
    }
}