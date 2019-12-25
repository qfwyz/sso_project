package com.hwua.filter;

import com.hwua.domain.User;
import com.hwua.mapper.UserMapper;
import com.hwua.service.UserService;
import com.hwua.util.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTFilter extends BasicHttpAuthenticationFilter {
    @Autowired
    private UserMapper userMapper;


    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //判断请求路径
        String servletPath = req.getServletPath();
        if("login".equals(servletPath.equals("login"))){
            try {
                //执行登录方法
                executeLogin(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //获取请求消息头信息或者路径信息    获取token
        String token = req.getHeader("token");
        if (token==null||token.trim()==""){
            token = req.getParameter("token");
            if (token==null||token.trim()==""){
                return true;
            }
        }
        //token不为空
        preHandle( request,  response);
        return false;
    }


    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String token = req.getHeader("token");
        //判断token的值是否是真的
        String username = JWTUtil.decodeToken(token, "username");
        User paramUser = new User();
        paramUser.setUsername(username);
        //校验用户是否存在
        User user = userMapper.selectByUsername(paramUser);
        if (user==null){
            //继续登录
            return true;
        }else{
            //不用登陆
            return false;
        }
    }
}
