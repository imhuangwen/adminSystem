package com.hw.admin.service.impl;

import com.hw.admin.mapper.ManagerMapper;
import com.hw.admin.model.Manager;
import com.hw.admin.service.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements IManagerService {
    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public Manager login(String loginName, String password) {
        return managerMapper.login(loginName.trim(), password.trim());
    }
}
