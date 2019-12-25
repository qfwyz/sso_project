package com.hwua.config;

import com.hwua.domain.User;
import com.hwua.mapper.UserMapper;
import com.hwua.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
       return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //把参数强转为UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        //获取用户名
        String username = usernamePasswordToken.getUsername();
        //根据用户名查询
        User user = null;
        try{
            User paramUser = new User();
            paramUser.setUsername(username);
           user = userMapper.selectByUsername(paramUser);
            //判断对象是否为空
            if (user!=null){
                //使用用户名作为盐
                ByteSource bytes = ByteSource.Util.bytes(username);
                //构建对象
                SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(usernamePasswordToken,user.getPassword(),bytes,getName());
                //获取当前的会话对象
                Subject subject = SecurityUtils.getSubject();
                return simpleAuthenticationInfo;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
