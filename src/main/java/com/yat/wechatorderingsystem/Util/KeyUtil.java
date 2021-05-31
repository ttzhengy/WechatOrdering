package com.yat.wechatorderingsystem.Util;

import java.util.Random;

public class KeyUtil {

    /**
     * 生成主键，格式：当前毫秒数+六位随机数
     * @return 当前毫秒数+六位随机数
     */
    public static synchronized String getUniqueKey(){

        // 生成六位随机数
        Random random = new Random();
        int i = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(i);
    }
}
