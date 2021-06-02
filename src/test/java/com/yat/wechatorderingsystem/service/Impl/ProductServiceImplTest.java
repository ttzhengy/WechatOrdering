package com.yat.wechatorderingsystem.service.Impl;

import com.github.pagehelper.PageInfo;
import com.yat.wechatorderingsystem.dto.CartDTO;
import com.yat.wechatorderingsystem.entity.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ProductServiceImplTest {

    @Autowired
    ProductServiceImpl productService;


    private CartDTO cartDTO1 = new CartDTO("1005",5);
    private CartDTO cartDTO2 = new CartDTO("1001",1);
    private CartDTO cartDTO3 = new CartDTO("1001",3);
    private CartDTO cartDTO4 = new CartDTO("1010",3);

    private List<CartDTO> cartDTOList = new ArrayList<>();

    @Test
    void selectById() {
        ProductInfo productInfo = productService.selectById(cartDTO1.getProductId());
        Assertions.assertEquals("艾雅法拉",productInfo.getProductName());
    }

    @Test
    void selectAll() {
        List<ProductInfo> productInfos = productService.selectAll(2, 4);
        PageInfo<ProductInfo> productInfoPageInfo = new PageInfo<>(productInfos);
        Assertions.assertEquals(4,productInfoPageInfo.getPageSize());
    }

    @Test
    void selectAllOnSale() {
        List<ProductInfo> productInfos = productService.selectAllOnSale();
        Assertions.assertNotEquals(0,productInfos.size());
    }

    @Test
    void selectByCategoryTypeIn() {
        List<ProductInfo> productInfos = productService.selectByCategoryTypeIn(3);
        Assertions.assertNotEquals(0,productInfos.size());
    }

    @Test
    @Transactional
    void increaseStock() {
        cartDTOList.add(cartDTO1);
        cartDTOList.add(cartDTO2);
        productService.increaseStock(cartDTOList);
        for (CartDTO cartDTO : cartDTOList) {
            String productId = cartDTO.getProductId();
            ProductInfo productInfo = productService.selectById(cartDTO.getProductId());
            log.info("商品名：{}，库存量：{}",productInfo.getProductName(),productInfo.getProductStock());
        }
    }

    @Test
    @Transactional
    void decreaseStock() {
        // 一般性测试
        cartDTOList.add(cartDTO1);
        cartDTOList.add(cartDTO2);

        productService.decreaseStock(cartDTOList);
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productService.selectById(cartDTO.getProductId());
            log.info("商品名：{}，库存量：{}",productInfo.getProductName(),productInfo.getProductStock());
        }
    }

    @Test
    @Transactional
    void decreaseStock2() {
        // 负库存测试
        cartDTOList.add(cartDTO1);
        cartDTOList.add(cartDTO3);

        productService.decreaseStock(cartDTOList);
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productService.selectById(cartDTO.getProductId());
            log.info("商品名：{}，库存量：{}",productInfo.getProductName(),productInfo.getProductStock());
        }
    }

    @Test
    @Transactional
    void decreaseStock3() {
        // 非法商品测试
        cartDTOList.add(cartDTO1);
        cartDTOList.add(cartDTO4);

        productService.decreaseStock(cartDTOList);
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productService.selectById(cartDTO.getProductId());
            log.info("商品名：{}，库存量：{}",productInfo.getProductName(),productInfo.getProductStock());
        }
    }

    @Test
    @Transactional
    void insert() {
        ProductInfo productInfo = new ProductInfo("1010", "aaa",
                new BigDecimal(100), 100, "xxx",
                "xx.jpg", 0, 3);
        productService.insert(productInfo);
        ProductInfo productInfo1 = productService.selectById("1010");
        Assertions.assertEquals("aaa",productInfo1.getProductName());
    }

    @Test
    @Transactional
    void update() {
        ProductInfo productInfo = productService.selectById("1004");
        productInfo.setCategoryType(1);
        productService.update(productInfo);
        ProductInfo productInfo1 = productService.selectById("1004");
        Assertions.assertEquals(1,productInfo1.getCategoryType());
    }
}