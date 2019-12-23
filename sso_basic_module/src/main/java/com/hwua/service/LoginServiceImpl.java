package com.hwua.service;

import com.hwua.dao.UserDao;
import com.hwua.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl {

    @Autowired
    private UserDao userDao;

    public User getUserByUsername(String username) {
        User one = userDao.getOne(username);
        return one;
    }
}
