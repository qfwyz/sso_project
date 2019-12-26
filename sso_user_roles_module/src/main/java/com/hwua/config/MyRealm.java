package com.hwua.config;

import com.hwua.domain.Permission;
import com.hwua.domain.Role;
import com.hwua.domain.User;
import com.hwua.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;


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

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取用户名
        String username = ((UsernamePasswordToken)principals.getPrimaryPrincipal()).getUsername();
        //构建临时用户
        User tempUser = new User();
        tempUser.setUsername(username);
        //根据用户名查询用户
        User user = userMapper.selectByUsername(tempUser);
        //构建对象
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //给用户关联角色
        Set<Role> roles = user.getRoles();
        Iterator<Role> iterator = roles.iterator();
        while (iterator.hasNext()){
            Role role = iterator.next();
            //设置到shiro的对象 SimpleAuthorizationInfo 里面
            simpleAuthorizationInfo.addRole(role.getName());
            //给角色关联权限
           Set<Permission> powers = role.getPermissions();
            Iterator<Permission> iterator1 = powers.iterator();
            while (iterator1.hasNext()){
                Permission permission = iterator1.next();
                //设置到shiro的对象 SimpleAuthorizationInfo 里面
                simpleAuthorizationInfo.addStringPermission(permission.getName());
            }
        }
        return simpleAuthorizationInfo;
    }
}
