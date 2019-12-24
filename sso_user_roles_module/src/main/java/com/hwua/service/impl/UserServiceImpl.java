package com.hwua.service.impl;

import com.hwua.domain.User;
import com.hwua.mapper.UserMapper;
import com.hwua.service.UserService;
import com.hwua.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseData login(User srcUser) {
        ResponseData responseData = new ResponseData();

        User user = userMapper.selectByUsername(srcUser);
        //判断用户名是否存在
        if (user==null){
            responseData.setCode(503);
            responseData.setMessage("用户名不存在");
            responseData.setT(srcUser);
            return responseData;
        }
        //判断密码是否正确
        if (user==null){
            responseData.setCode(503);
            responseData.setMessage("用户名不存在");
            responseData.setT(srcUser);
            return responseData;
        }
        if (user==null){
            responseData.setCode(503);
            responseData.setMessage("用户名不存在");
            responseData.setT(srcUser);
            return responseData;
        }
        if (user==null){
            responseData.setCode(503);
            responseData.setMessage("用户名不存在");
            responseData.setT(srcUser);
            return responseData;
        }

        return null;
    }
}
