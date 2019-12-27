package com.hwua.filter;

import com.hwua.mapper.UserMapper;
import com.hwua.util.MyJwtToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter {
    @Autowired
    private UserMapper userMapper;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        log.info("--------------------isAccessAllowed-------------------------");
        if (isLoginAttempt(request, response)){
            try {
                executeLogin(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        log.info("--------------------isLoginAttempt-------------------------");
        HttpServletRequest req = (HttpServletRequest) request;
        //优化返回方案
        //判断请求头是否携带token
        String token = req.getHeader("authorization");
        if (token==null||token.trim().equals("")){
            token = req.getParameter("authorization");
        }
        return token!=null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        log.info("--------------------executeLogin-------------------------");
        HttpServletRequest req = (HttpServletRequest) request;
        //获取请求消息头信息或者路径信息    获取token
        String token = req.getHeader("authorization");
        if (token==null||token.trim()==""){
            token = req.getParameter("authorization");
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
