package com.yat.wechatorderingsystem.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum {
    ONSALE(0,"商品上架"),
    OFFSHELVES(1,"商品下架");

    private Integer status;
    private String message;

    private ProductStatusEnum(Integer status, String message){
        this.status = status;
        this.message = message;
    }
}
