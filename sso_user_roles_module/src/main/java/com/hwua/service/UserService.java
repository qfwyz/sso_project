package com.hwua.service;

import com.hwua.domain.User;
import com.hwua.util.ResponseData;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseData login(User user);

}
