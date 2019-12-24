package com.hwua.config;

import com.hwua.domian.LoginInfo;
import com.hwua.domian.Power;
import com.hwua.domian.Role;
import com.hwua.domian.User;
import com.hwua.service.LoginInfoService;
import com.hwua.service.UserService;
import com.sun.xml.internal.ws.wsdl.writer.document.StartWithExtensionsType;
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
    private LoginInfoService loginInfoService;
    @Autowired
    private UserService userService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取用户名
        String username = ((UsernamePasswordToken)principals.getPrimaryPrincipal()).getUsername();
        System.err.println(username);
        //根据用户名查询用户
        User user = userService.getUserByUsername(username);
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
            Set<Power> powers = role.getPowers();
            Iterator<Power> iterator1 = powers.iterator();
            while (iterator1.hasNext()){
                Power power = iterator1.next();
                //设置到shiro的对象 SimpleAuthorizationInfo 里面
                simpleAuthorizationInfo.addStringPermission(power.getName());
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //把参数强转为UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        //获取用户名
        String username = usernamePasswordToken.getUsername();
        //根据用户名查询
        LoginInfo loginInfo = null;
        try{
            loginInfo = loginInfoService.getLoginInfoByUsername(username);
            //判断对象是否为空
            if (loginInfo!=null){
                //使用用户名作为盐
                ByteSource bytes = ByteSource.Util.bytes(username);
                //构建对象
                SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(usernamePasswordToken,loginInfo.getPassword(),bytes,getName());
                //获取当前的会话对象
                Subject subject = SecurityUtils.getSubject();
                //清空之前的缓存数据
                //clearCachedAuthenticationInfo(subject.getPrincipals());
                //绑定域对象
                subject.getSession().setAttribute("currentUser",usernamePasswordToken);
                return simpleAuthenticationInfo;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
