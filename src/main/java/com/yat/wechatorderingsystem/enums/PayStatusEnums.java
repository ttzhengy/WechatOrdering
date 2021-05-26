package com.yat.wechatorderingsystem.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnums {
    WAIT(0,"等待支付"),
    PAYED(1,"已支付");

    private Integer status;
    private String message;

    private PayStatusEnums(Integer status, String message){
        this.status = status;
        this.message = message;
    }
}
