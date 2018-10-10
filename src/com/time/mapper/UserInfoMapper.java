package com.time.mapper;

import com.time.pojo.UserInfo;

import java.sql.SQLException;

/**
 * 用户个人信息
 */
public interface UserInfoMapper {
    /**
     * 根据用户Id查找用户个人信息
     * @param userId
     * @return
     */
    UserInfo findUserInfo(Integer userId) throws SQLException;

}
