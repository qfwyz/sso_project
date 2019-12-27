package com.hwua.action;

import com.hwua.domain.User;
import com.hwua.service.UserService;
import com.hwua.util.JWTUtil;
import com.hwua.util.PasswordUtil;
import com.hwua.util.ResponseData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAction {
    @Autowired
    private UserService userService;


    @PostMapping("/user/login")
    public ResponseData login(@RequestBody User user) throws Exception{
        //拆解用户名
        String username = user.getUsername();
        //连接服务器校验
        User resultUser = userService.getUserByUsername(username);
        String password = user.getPassword();
        //比对信息
        if (resultUser==null||!(PasswordUtil.checkPassword(password,resultUser.getPassword(),username,2019))){
            throw new Exception("用户名或密码错误!!!");
        }
        //构建返回对象,除此之外的错误对象交给全局处理异常处理
        ResponseData<User> userResponseData = new ResponseData<>();
        userResponseData.setCode(0);
        userResponseData.setT(user);
        userResponseData.setMessage("login success");
        userResponseData.setAccessToken(JWTUtil.createToken(username,password));
        return userResponseData;
    }

    @PutMapping("/user/pwd")
    public void pwd(String oldPwd,String newPwd,String rePass) throws Exception{
        System.out.println(oldPwd+"   "+newPwd+"   "+rePass);

    }

}
