package com.hwua.util;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 提供原来的UsernamePasswordToken的增强版
 * 此时
 * username   是使用密码作为凭证加密之后的用户名
 * password   原来的密码还是原来的密码
 */
public class MyJwtToken implements AuthenticationToken {
    //提供私有属性保存token对象
    //这个时候的用户名和密码一样
    // 上面的注释作废,为安全起见,必须进行密码的校验
    private String token;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return password;
    }
}
