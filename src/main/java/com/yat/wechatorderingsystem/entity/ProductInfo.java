package com.yat.wechatorderingsystem.entity;

import com.yat.wechatorderingsystem.enums.ProductStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo {
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    private Integer productStatus = 0;
    // private Integer productStatus = ProductStatusEnum.ONSALE.getStatus();
    private Integer categoryType;
}
