package com.yat.wechatorderingsystem.enums;

public enum OrderStatusEnums {
    NEW(0,"新订单"),
    FINISHED(1,"订单已完成"),
    CANCEL(2,"订单已取消");

    private int status;
    private String message;

    private OrderStatusEnums(int status,String message){
        this.status = status;
        this.message = message;
    }
}
