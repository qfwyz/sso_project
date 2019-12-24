package com.hwua.util;

import java.security.SecureRandom;

/**
 * 负责生成盐
 */
public class SaltUtil {
    //要求生成一个十六进制的字符串
    public static String getSalt(){
     //构建加密类对象
        SecureRandom secureRandom = new SecureRandom();
        //构建byte类型的数组
        byte[] bytes = new byte[20];
        secureRandom.nextBytes(bytes);
        return new String(bytes);
    }
}
