package com.yat.wechatorderingsystem.Util;

public class MathsUtil {

    public static Boolean equals(Double a, Double b){
        double result = Math.abs(a-b);
        return result < 0.01;
    }
}
