package com.hw.admin.controller;

import com.hw.admin.exception.BusinessException;
import com.hw.admin.model.Manager;
import com.hw.admin.service.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员控制器
 */
@RestController
@RequestMapping("/admin")
public class ManagerContoller {
    @Autowired
    private IManagerService managerService;

    @GetMapping(path = "/test/{b}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Manager test(@PathVariable("b") Boolean b) {
        if (b) {
            throw new BusinessException("这是一个自定义异常");
        }
        return managerService.login("admin","123456");
    }
}
