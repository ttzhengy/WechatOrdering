package com.yat.wechatorderingsystem.service;

import com.yat.wechatorderingsystem.dto.CartDTO;
import com.yat.wechatorderingsystem.entity.ProductInfo;

import java.util.List;

public interface ProductService {

    // 根据id查询商品
    ProductInfo selectById(String id);

    // 查询所有商品
    List<ProductInfo> selectAll(Integer pageNum, Integer size);

    // 查询所有上架商品
    List<ProductInfo> selectAllOnSale();

    // 根据商品类目查询商品
    List<ProductInfo> selectByCategoryTypeIn(Integer type);

    // 加库存
    void increaseStock(List<CartDTO> cartDTOList);

    // 减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    // 增加商品
    ProductInfo insert(ProductInfo productInfo);

    // 修改商品
    ProductInfo update(ProductInfo productInfo);
}