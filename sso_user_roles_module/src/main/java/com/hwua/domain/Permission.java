package com.hwua.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Permission {
    private String id;
    private String code;
    private String name;
    private String perms;
    private String url;
    private String method;
    private String pid;
    private Integer orderNum;
    private Byte type;
    private Byte status;
    private Date createTime;
    private Date updateTime;
    private Byte deleted;
}