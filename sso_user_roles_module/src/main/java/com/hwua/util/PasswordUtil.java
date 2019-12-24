package com.hwua.util;

import java.security.MessageDigest;
import java.util.Random;

/**
 * 密码工具类
 */
public class PasswordUtil {
    /**
     * byte[]字节数组 转换成 十六进制字符串
     * @param arr 要转换的byte[]字节数组
     * @return  String 返回十六进制字符串
     */
    private static String byteArrayToHexString(byte[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }
    /**
     * MD5加密,并把结果由字节数组转换成十六进制字符串
     * @param password 要加密的内容
     * @param hashAlgorithm 设置加密的方式
     * @return String 返回加密后的十六进制字符串
     */
    private static String toStringHex(String password,String hashAlgorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(hashAlgorithm);
            byte[] digest = md.digest(password.getBytes());
            return byteArrayToHexString(digest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
        判断两个密码是否一致
     */
    public static boolean checkPassword(String password, String md5str,String hashAlgorithm,int number) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5str.charAt(i);
            cs1[i / 3 * 2 + 1] = md5str.charAt(i + 2);
            cs2[i / 3] = md5str.charAt(i + 1);
        }
        String Salt = new String(cs2);
        while(number>0){
            password=toStringHex(password + Salt,hashAlgorithm);
            number--;
        }

        return password.equals(String.valueOf(cs1));
    }



    /**
     * 生成含有随机盐的密码
     *
     * @param password 要加密的密码
     * @param hashAlgorithm 加密的方式
     * @param number 加密的次数
     *
     * @return String 含有随机盐的密码
     */
    public static String getSaltMD5(String password,String hashAlgorithm,int number) {
        // 生成一个16位的随机数
        Random random = new Random();
        StringBuilder sBuilder = new StringBuilder(16);
        sBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
        int len = sBuilder.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sBuilder.append("0");
            }
        }
        // 生成最终的加密盐
        String salt = sBuilder.toString();
        // 多次盐值加密
        while(number>0){
            password = toStringHex(password + salt,hashAlgorithm);
            number--;
        }
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return String.valueOf(cs);
    }

}
