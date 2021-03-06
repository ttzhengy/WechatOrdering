package com.yat.wechatorderingsystem.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnums {
    NEW(0,"新订单"),
    FINISHED(1,"订单已完成"),
    CANCEL(2,"订单已取消");

    private Integer status;
    private String message;

    private OrderStatusEnums(Integer status,String message){
        this.status = status;
        this.message = message;
    }
}
