package com.yat.wechatorderingsystem.service.Impl;

import com.yat.wechatorderingsystem.dao.ProductCategoryDao;
import com.yat.wechatorderingsystem.entity.ProductCategory;
import com.yat.wechatorderingsystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    ProductCategoryDao productCategoryDao;

    @Override
    public ProductCategory selectById(Integer id) {
        return productCategoryDao.selectById(id);
    }

    @Override
    public List<ProductCategory> selectAll() {
        return productCategoryDao.selectAll();
    }

    @Override
    public List<ProductCategory> selectByTypeIn(List<Integer> type) {
        return productCategoryDao.selectByType(type);
    }

    @Override
    public ProductCategory insert(ProductCategory productCategory) {
        productCategoryDao.insertProductCategory(productCategory);
        return productCategory;
    }

    @Override
    public ProductCategory update(ProductCategory productCategory) {
        productCategoryDao.updateProductCategory(productCategory);
        return productCategory;
    }
}
