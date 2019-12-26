package com.hwua.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Role {
    private String id;
    private String name;
    private String description;
    private Byte status;
    private Date createTime;
    private Date updateTime;
    private Byte deleted;

    //一个角色可以对应多个权限    安全起见    配置多个权限
    private Set<Permission> permissions = new HashSet<>();
}