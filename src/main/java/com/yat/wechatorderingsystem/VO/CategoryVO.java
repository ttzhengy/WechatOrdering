package com.yat.wechatorderingsystem.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CategoryVO {

    // 商品类目名
    @JsonProperty(value = "name")
    private String categoryName;

    // 商品种类
    @JsonProperty(value = "type")
    private Integer categoryType;

    // 商品列表
    @JsonProperty(value = "foods")
    private List<ProductVO> list;
}
