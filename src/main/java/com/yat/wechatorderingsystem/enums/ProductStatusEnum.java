package com.yat.wechatorderingsystem.enums;

public enum ProductStatusEnum {
    ONSALE(0,"商品上架"),
    OFFSHELVES(1,"商品下架");

    private int status;
    private String message;

    private ProductStatusEnum(int status, String message){
        this.status = status;
        this.message = message;
    }
}
