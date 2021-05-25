package com.yat.wechatorderingsystem.dao;

import com.yat.wechatorderingsystem.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Mapper
public interface ProductCategoryDao {
    public ProductCategory selectById(int id);

    public List<ProductCategory> selectByType(List<Integer> types);

    public void insertProductCategory(ProductCategory productCategory);

    public void updateProductCategory(ProductCategory productCategory);
}
