package com.hwua.test;

import com.hwua.util.PasswordUtil;

public class PasswordUtilTest {

    public static void main(String[] args) {
        String md51 = PasswordUtil.getSaltMD5("123456", "md5",1);
        String md52 = PasswordUtil.getSaltMD5("123456", "md5",2);
        System.out.println(md51);
        System.out.println(md52);
        boolean b1 = PasswordUtil.checkPassword("123341334456", md51, "md5",1);
        boolean b2 = PasswordUtil.checkPassword("1233414456", md52, "md5",2);
        System.out.println(b1);
        System.out.println(b2);
    }
}
