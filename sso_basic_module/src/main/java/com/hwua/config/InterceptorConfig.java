package com.hwua.config;

import com.hwua.interceptor.LoginInterceptor;
import org.apache.tomcat.util.security.Escape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加过滤器的时候,再添加一个路径
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
    }

    //构建登录拦截器
    @Bean(name = "loginInterceptor")
    public LoginInterceptor getLoginInterceptor(){
        return new LoginInterceptor();
    }
}
