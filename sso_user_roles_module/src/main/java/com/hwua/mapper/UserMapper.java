package com.hwua.mapper;


import com.hwua.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    @Select("select * from user where username=#{username}")
    User selectByUsername(User user);

    User selectUserInfoByUsername(String username);
}