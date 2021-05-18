package com.yat.wechatorderingsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory {
    private Integer categoryId;
    private String categoryName;
    private String categoryType;
}
