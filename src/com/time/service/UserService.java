package com.time.service;

import com.time.pojo.User;

import java.sql.SQLException;

public interface UserService {
    /**
     * 用户登陆
     * @param user
     * @return
     */
    User userLogin(User user) throws SQLException;

    /**
     * 用户注册
     * @param user
     * @throws SQLException
     */
    void userRegist(User user) throws SQLException;

    /**
     * 根据code查找用户
     * @param code
     * @return
     */
    User findUserByCode(String code) throws SQLException;

    /**
     * 更新用户状态
     * @param user
     * @return
     */
    void updateUserState(User user) throws SQLException;

    /**
     * 校验用户名
     * @param userName
     * @return
     * @throws SQLException
     */
    boolean checkUserName(String userName) throws SQLException;

    /**
     * 更新用户信息
     * @param user
     */
    Boolean updateUser(User user);
}

