package com.hwua.config;

import com.hwua.domain.Permission;
import com.hwua.domain.Role;
import com.hwua.domain.User;
import com.hwua.mapper.UserMapper;
import com.hwua.service.UserService;
import com.hwua.util.JWTUtil;
import com.hwua.util.MyJwtToken;
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
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof MyJwtToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取token
        String token = authenticationToken.getPrincipal().toString();
        //获取密码
        String password = authenticationToken.getCredentials().toString();
        //解密token,获取真实用户名
        String username = JWTUtil.decodeToken(token);
        //判断用户名不为空
        if (username==null||(!JWTUtil.checkToken(username,password,token))){
            throw new AuthenticationException("用户名错误!!!");
        }
        //判断密码是否正确
        User user = userService.getUserByUsername(username);
        if (!user.getPassword().equals(password)){
            throw new AuthenticationException("密码错误!!!");
        }
        //使用用户名作为盐
        ByteSource bytes = ByteSource.Util.bytes(username);
        //构建对象
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(token,user.getPassword(),bytes,getName());
        return simpleAuthenticationInfo;
     }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取用户名
        String username = JWTUtil.decodeToken(principals.getPrimaryPrincipal().toString());
        //根据用户名查询用户
        User user = userService.getUserInfoByUsername(username);
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
