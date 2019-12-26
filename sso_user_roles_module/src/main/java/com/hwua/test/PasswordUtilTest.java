package com.hwua.test;

import com.hwua.util.PasswordUtil;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.UUID;

public class PasswordUtilTest {
    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456","zhangsan",2019);
        System.out.println(md5Hash);
        System.out.println(md5Hash.toString());
        System.out.println(UUID.randomUUID().toString());
    }
}
