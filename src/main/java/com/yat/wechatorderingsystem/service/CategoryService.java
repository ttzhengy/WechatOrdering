package com.yat.wechatorderingsystem.service;

import com.yat.wechatorderingsystem.entity.ProductCategory;

import java.util.List;

public interface CategoryService {

    // 根据类目id查询单个类目
    ProductCategory selectById(Integer id);

    // 查询所有类目
    List<ProductCategory> selectAll();

    // 根据类目类型查询多个类目
    List<ProductCategory> selectByTypeIn(List<Integer> type);

    // 增加商品类目
    ProductCategory insert(ProductCategory productCategory);

    // 修改商品类目
    ProductCategory update(ProductCategory productCategory);
}
