package com.hwua.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User {
    private String id;
    private String username;
    private String salt;
    private String password;
    private String phone;
    private String deptId;
    private String realName;
    private String nickName;
    private String email;
    private Byte status;
    private Byte sex;
    private Byte deleted;
    private String createId;
    private String updateId;
    private Byte createWhere;
    private Date createTime;
    private Date updateTime;

    //一个用户可以对应多个角色   -----  为安全起见可以写多个
    private Set<Role> roles = new HashSet<>();
}