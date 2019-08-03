package com.hw.admin.service;

import com.hw.admin.model.Manager;

public interface IManagerService {
    /**
     * 登录并返回信息
     * @param loginName
     * @param password
     * @return
     */
    Manager login(String loginName, String password);
}
