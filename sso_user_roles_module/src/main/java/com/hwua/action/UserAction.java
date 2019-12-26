package com.hwua.action;

import com.hwua.domain.User;
import com.hwua.util.ResponseData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAction {
    @PostMapping("/user/login")
    public ResponseData login(@RequestBody User user){
        //获取当前对象
        Subject subject = SecurityUtils.getSubject();
        //构建UsernamePasswordToken对象
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(),user.getPassword());
       //执行登录
       subject.login(usernamePasswordToken);
       //构建返回对象,除此之外的错误对象交给全局处理异常处理
        ResponseData<User> userResponseData = new ResponseData<>();
        userResponseData.setCode(200);
        userResponseData.setT(user);
        userResponseData.setMessage("login success");
        return userResponseData;
    }


}
