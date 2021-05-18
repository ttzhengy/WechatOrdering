package com.yat.wechatorderingsystem.dao;

import com.yat.wechatorderingsystem.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductCategoryDao {
    public ProductCategory selectById(int id);

    public void insertProductCategory(ProductCategory productCategory);
}
