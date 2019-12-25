package com.hwua.test;

import com.hwua.util.PasswordUtil;

public class PasswordUtilTest {

    public static void main(String[] args) {
        String md51 = PasswordUtil.getSaltMD5("123456", "md5",1);
        System.out.println(md51);
        boolean b1 = PasswordUtil.checkPassword("123456", "654603951960b8e80f55451356dd4634a015241e6e10420a", "md5",1);
        System.out.println(b1);
    }
}
