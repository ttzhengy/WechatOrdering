package com.yat.wechatorderingsystem.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductVO {

    // 商品id
    @JsonProperty(value = "id")
    private String productId;

    // 商品名称
    @JsonProperty(value = "name")
    private String productName;

    // 商品价格
    @JsonProperty(value = "price")
    private BigDecimal productPrice;

    // 商品描述
    @JsonProperty(value = "description")
    private String productDescription;

    // 商品小图
    @JsonProperty(value = "icon")
    private String productIcon;
}
