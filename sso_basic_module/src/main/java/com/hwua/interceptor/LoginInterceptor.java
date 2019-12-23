package com.hwua.interceptor;

import com.auth0.jwt.JWT;
import com.hwua.annotation.UseLogin;
import com.hwua.domain.User;
import com.hwua.service.JWTServiceImpl;
import com.hwua.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

//登录拦截器
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginServiceImpl loginService;
    @Autowired
    private JWTServiceImpl jwtService;


    //预处理
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handlerObject) throws Exception{
        String myToken = request.getHeader("token");
        System.out.println(
                myToken
        );
        //获取请求方法对象
        if (!(handlerObject instanceof HandlerMethod)){
            return true;
        }
        //强转
        HandlerMethod handlerMethod = (HandlerMethod) handlerObject;
        //获取方法的反射对象
        Method method = handlerMethod.getMethod();
        //获取该方法的注解
        UseLogin userLogin = method.getDeclaredAnnotation(UseLogin.class);
        //如果有该注解判断这个注解的值
        if(userLogin!=null){
            if (userLogin.required()){
                //获取请求携带的数据
                String token = request.getHeader("token");
                //判断token是否存在
                if (token==null||token.trim().equals("")){
                    //直接放行登录
                    return true;
                } else{
                    //判断token的值是否是真的
                    String username = jwtService.decodeTokenByKey(token, "username");
                    //校验用户是否存在
                    User user = loginService.getUserByUsername(username);
                    if (user==null){
                        //继续登录
                        return false;
                    }else{
                        //不用登陆

                        return true;
                    }
                }
            }else{
                return true;
            }
        }
        return false;
    }
}
