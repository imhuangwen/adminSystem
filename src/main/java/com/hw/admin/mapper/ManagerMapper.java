package com.hw.admin.mapper;

import com.hw.admin.model.Manager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerMapper {
    /**
     * 登录验证并返回用户信息
     * @param loginName
     * @param password
     * @return
     */
    Manager login(@Param("loginName") String loginName, @Param("password") String password);
}
