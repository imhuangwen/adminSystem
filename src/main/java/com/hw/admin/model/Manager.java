package com.hw.admin.model;

import lombok.Data;

/**
 * 管理员实体
 */
@Data
public class Manager extends BaseModel{
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 性别
     */
    private Integer sex;
}
