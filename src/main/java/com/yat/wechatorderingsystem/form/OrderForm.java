package com.yat.wechatorderingsystem.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderForm {

    @NotEmpty(message = "姓名为空")
    private String name;

    @NotEmpty(message = "电话为空")
    private String phone;

    @NotEmpty(message = "地址为空")
    private String address;

    @NotEmpty(message = "openid为空")
    private String openid;

    @NotEmpty(message = "购物车为空")
    private String items;
}
