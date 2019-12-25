package com.hwua.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(){
        //过滤器工厂对象
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //属性注值
        shiroFilterFactoryBean.setSecurityManager(getSecurityManager());
        //登录界面
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        //成功之后的界面
        shiroFilterFactoryBean.setSuccessUrl("/main.html");
        //获取过滤器的集合
        Map<String, String> filterChainDefinitionMap = shiroFilterFactoryBean.getFilterChainDefinitionMap();
        //设置集合的内容
        /*
        第一个参数是  对应的资源
        第二个参数是  过滤器的名字
         */
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/error/**","anon");
        filterChainDefinitionMap.put("/images/**","anon");
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/layui/**","anon");
        filterChainDefinitionMap.put("/user/login","anon");

        filterChainDefinitionMap.put("/**","jWTFilter");
        return shiroFilterFactoryBean;
    }


    //构建安全管理器
    @Bean("securityManager")
    public DefaultWebSecurityManager getSecurityManager(){
        //构建DefaultWebSecurityManager对象
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //设置自定义规则
        defaultWebSecurityManager.setRealm(getMyRealm());
        return defaultWebSecurityManager;
    }

    //提供自定义的realm类对象
    @Bean("myRealm")
    public MyRealm getMyRealm(){
        MyRealm myRealm = new MyRealm();
        //设置加密算法
        myRealm.setCredentialsMatcher(getHashedCredentialsMatcher());
        //myRealm.isCachingEnabled()
        return myRealm;
    }

    //提供自定义的盐值加密的类
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher getHashedCredentialsMatcher(){
        //构建盐值加密类,指定加密方式为MD5
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher("MD5");
        //加盐次数
        hashedCredentialsMatcher.setHashIterations(2019);
        return hashedCredentialsMatcher;
    }
}
