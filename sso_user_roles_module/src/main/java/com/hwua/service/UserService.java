package com.hwua.service;

import com.hwua.domain.User;
import com.hwua.util.ResponseData;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    //用户登录操作
    //User login(User user);
    User getUserByUsername(User user);

    //根据用户名查询用户权限
    User getUserInfoByUsername(String username);
}
