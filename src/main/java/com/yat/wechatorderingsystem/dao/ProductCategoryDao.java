package com.yat.wechatorderingsystem.dao;

import com.yat.wechatorderingsystem.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Mapper
public interface ProductCategoryDao {

    ProductCategory selectById(int id);

    List<ProductCategory> selectByType(List<Integer> types);

    void insertProductCategory(ProductCategory productCategory);

    void updateProductCategory(ProductCategory productCategory);
}
