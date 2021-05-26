package com.yat.wechatorderingsystem.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnums {
    WAIT(0,"新订单"),
    PAYED(1,"订单已完成");

    private Integer status;
    private String message;

    private PayStatusEnums(Integer status, String message){
        this.status = status;
        this.message = message;
    }
}
