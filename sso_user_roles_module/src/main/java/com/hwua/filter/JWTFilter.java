package com.hwua.filter;

import com.hwua.domain.User;
import com.hwua.mapper.UserMapper;
import com.hwua.service.UserService;
import com.hwua.util.JWTUtil;
import com.hwua.util.MyJwtToken;
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
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)){
            try {
                executeLogin(request,response);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        //优化返回方案
        //判断请求头是否携带token
        String token = req.getHeader("token");
        if (token==null||token.trim().equals("")){
            token = req.getParameter("token");
        }
        return token!=null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        //获取请求消息头信息或者路径信息    获取token
        String token = req.getHeader("token");
        if (token==null||token.trim()==""){
            token = req.getParameter("token");
        }
        //上述代码确保了token一定不为空
        /**
         * login 方法的参数是AuthenticationToken 对象
         * 两个办法:
         * 1)构建UsernamPasswordToken
         * 2)自定义一个token对象
         */
        MyJwtToken myJwtToken = new MyJwtToken();
        myJwtToken.setToken(token);
        getSubject(request, response).login(myJwtToken);
        return true;
    }



}
