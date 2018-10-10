package com.time.mapper;

import com.time.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

/**
 * 用户登陆接口
 */
public interface UserMapper {
    /**
     * 查找用户
     * @param user
     * @return
     */
    User findUser(User user) throws SQLException;

    /**
     * 注册用户
     * @param user
     * @throws SQLException
     */
    void addUser(User user) throws SQLException;

    /**
     * 根据code查找用户
     * @param code
     * @return
     * @throws SQLException
     */
    User findUserByCode(@Param("code") String code)  throws SQLException;

    /**
     * 更新用户状态，并设置code为空
     * @param user
     * @throws SQLException
     */
    void updateUserState(User user) throws SQLException;

    /**
     * 查询用户名
     * @param userName
     * @return
     * @throws SQLException
     */
    int checkUserName(String userName) throws SQLException;

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int updateUserPassword(User user);
}
