package com.hwua.action;

import com.hwua.domain.ResponseData;
import com.hwua.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAction {

    @GetMapping("getUser01")
    public ResponseData<User> getUser01(){
        ResponseData<User> userResponseData = new ResponseData<>();
        userResponseData.setCode(200);
        userResponseData.setMessage("查询成功!!!");
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        userResponseData.setT(user);

        return userResponseData;
    }


    @GetMapping("getUser02")
    public ResponseData<User> getUser02(){
        ResponseData<User> userResponseData = new ResponseData<>();
        userResponseData.setCode(200);
        userResponseData.setMessage("查询成功!!!");
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        userResponseData.setT(user);
        System.out.println(3/0);
        return userResponseData;
    }
}
