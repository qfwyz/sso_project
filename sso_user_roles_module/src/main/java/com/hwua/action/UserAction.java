package com.hwua.action;

import com.hwua.domain.User;
import com.hwua.util.ResponseData;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAction {

    @PostMapping("/user/login")
    public ResponseData login(@RequestBody User user){

        return new ResponseData();
    }
}
