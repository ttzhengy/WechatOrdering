package com.yat.wechatorderingsystem.dao;

import com.yat.wechatorderingsystem.entity.ProductInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductInfoDao {

    ProductInfo selectById(String id);

    List<ProductInfo> selectAll();

    List<ProductInfo> selectByCategoryType(Integer type);

    List<ProductInfo> selectByProductStatus(Integer status);

    boolean insertProductInfo(ProductInfo productInfo);

    boolean updateProductInfo(ProductInfo productInfo);
}
