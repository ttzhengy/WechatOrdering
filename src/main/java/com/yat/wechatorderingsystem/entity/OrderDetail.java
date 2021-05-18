package com.yat.wechatorderingsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    private String detailId;
    private String orderId;
    private String productId;
    private String produceName;
    private String productPrice;
    private String productIcon;
}
