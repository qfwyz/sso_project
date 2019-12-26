package com.hwua.util;

import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;

import java.security.MessageDigest;
import java.util.Random;

/**
 * 密码工具类
 */
public class PasswordUtil {
    /*
        判断两个密码是否一致
     */
    public static boolean checkPassword(String password, String md5str,String username,int number) {
        //使用盐进行密码加密
        Md5Hash md5Hash = new Md5Hash(password,username,number);
        //比对密码
        return  (md5str.equals(md5Hash))?true:false;
    }
}
