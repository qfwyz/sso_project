package com.hwua.util;

import org.apache.shiro.authc.AuthenticationToken;

public class MyJwtToken implements AuthenticationToken {
    //提供私有属性保存token对象
    //这个时候的用户名和密码一样
    private String  token;

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
        return token;
    }
}
