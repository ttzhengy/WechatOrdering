package com.yat.wechatorderingsystem.service.Impl;

import com.github.pagehelper.PageHelper;
import com.sun.xml.internal.bind.v2.TODO;
import com.yat.wechatorderingsystem.dao.ProductInfoDao;
import com.yat.wechatorderingsystem.dto.CartDTO;
import com.yat.wechatorderingsystem.entity.ProductInfo;
import com.yat.wechatorderingsystem.enums.ResultEnum;
import com.yat.wechatorderingsystem.exception.SellException;
import com.yat.wechatorderingsystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductInfoDao productInfoDao;

    @Override
    public ProductInfo selectById(String id) {
        return productInfoDao.selectById(id);
    }

    @Override
    public List<ProductInfo> selectAll(Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum,size);
        List<ProductInfo> productInfos = productInfoDao.selectAll();
        return productInfos;
    }

    @Override
    public List<ProductInfo> selectAllOnSale() {
        return productInfoDao.selectByProductStatus(0);
    }

    @Override
    public List<ProductInfo> selectByCategoryTypeIn(Integer type) {
        return productInfoDao.selectByCategoryType(type);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo product = productInfoDao.selectById(cartDTO.getProductId());
            // 判断商品id是否存在
            if (product==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            // 修改库存
            int result = product.getProductStock() + cartDTO.getProductQuantity();
            product.setProductStock(result);
            productInfoDao.updateProductInfo(product);
        }
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo product = productInfoDao.selectById(cartDTO.getProductId());
            // 判断商品id是否存在
            if (product == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            int result = product.getProductStock() - cartDTO.getProductQuantity();
            // 判断库存是否充足
            if (result<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            // 修改库存
            product.setProductStock(result);
            productInfoDao.updateProductInfo(product);
        }
    }

    @Override
    public ProductInfo insert(ProductInfo productInfo) {
        productInfoDao.insertProductInfo(productInfo);
        return productInfo;
    }

    @Override
    public ProductInfo update(ProductInfo productInfo) {
        productInfoDao.updateProductInfo(productInfo);
        return productInfo;
    }
}
