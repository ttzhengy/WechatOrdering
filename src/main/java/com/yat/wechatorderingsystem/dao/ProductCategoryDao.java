package com.yat.wechatorderingsystem.dao;

import com.yat.wechatorderingsystem.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Mapper
public interface ProductCategoryDao {

    ProductCategory selectById(int id);

    List<ProductCategory> selectAll();

    List<ProductCategory> selectByType(List<Integer> types);

    boolean insertProductCategory(ProductCategory productCategory);

    boolean updateProductCategory(ProductCategory productCategory);
}
