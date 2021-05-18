package com.yat.wechatorderingsystem.entity;

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
    private String productID;
    private String productName;
    private BigDecimal price;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    private Short productStatus;
    private Integer categoryType;
}
