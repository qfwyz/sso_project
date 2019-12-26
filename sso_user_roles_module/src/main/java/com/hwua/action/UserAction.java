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

        ResponseData<User> userResponseData = new ResponseData<>();
        userResponseData.setCode(200);
        return userResponseData;
    }
}
