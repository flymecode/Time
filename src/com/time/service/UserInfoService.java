package com.time.service;

import com.time.pojo.UserInfo;

import java.sql.SQLException;

public interface UserInfoService {

    /**
     * 查找用户个人信息
     * @param id
     * @return
     */
    UserInfo findUserInfo(Integer id) throws SQLException;
}
