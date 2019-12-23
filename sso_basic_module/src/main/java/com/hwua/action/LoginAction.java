package com.hwua.action;

import com.alibaba.fastjson.JSONObject;
import com.hwua.annotation.UseLogin;
import com.hwua.domain.User;
import com.hwua.service.JWTServiceImpl;
import com.hwua.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginAction {
    @Autowired
    private LoginServiceImpl loginService;
    @Autowired
    private JWTServiceImpl jwtService;

    @PostMapping("login")
    @UseLogin(required = false)
    public JSONObject login(String username,String password) throws Exception{
        //构建JSONObject对象
        JSONObject jsonObject = new JSONObject();
        User user = loginService.getUserByUsername(username);
        if (user!=null&&user.getPassword().equals(password)){
            //构建成功对象
            String token = jwtService.encodeToken(user);
            jsonObject.put("token",token);
            jsonObject.put("status",200);
            jsonObject.put("info","success");
        }else{
            jsonObject.put("token","noToken");
            jsonObject.put("status",302);
            jsonObject.put("info","failed");
        }
        return jsonObject;
    }

}
