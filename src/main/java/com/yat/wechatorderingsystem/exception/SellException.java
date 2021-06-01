package com.yat.wechatorderingsystem.exception;

import com.yat.wechatorderingsystem.enums.ResultEnum;

public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMsg());

        this.code = resultEnum.getCode();
    }
}
