package com.time.service.impl;


import com.time.mapper.UserMapper;
import com.time.pojo.User;
import com.time.service.UserService;
import com.time.utils.MD5Utils;
import com.time.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;


public class UserServiceImpl implements UserService {

    @Override
    public User userLogin(User user) throws SQLException {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        user.setPassWord(MD5Utils.md5(user.getPassWord()));
        user = userMapper.findUser(user);
        if (null == user) {
            throw new RuntimeException("密码或账号错误");
        } else if (user.getState() == 0) {
            throw new RuntimeException("用户未激活");
        } else {
            return user;
        }
    }

    @Override
    public void userRegist(User user) throws SQLException {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        String passWord = MD5Utils.md5(user.getPassWord());
        user.setPassWord(passWord);
        userMapper.addUser(user);
        sqlSession.commit();
    }

    @Override
    public User findUserByCode(String code) throws SQLException {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        return userMapper.findUserByCode(code);
    }

    @Override
    public void updateUserState(User user) throws SQLException {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        user.setState(1);
        user.setCode(null);
        userMapper.updateUserState(user);
        sqlSession.commit();
    }

    @Override
    public boolean checkUserName(String userName) throws SQLException {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int result = userMapper.checkUserName(userName);
        if(result >= 1){
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateUser(User user) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        String passWord = MD5Utils.md5(user.getPassWord());
        user.setPassWord(passWord);
        int result = userMapper.updateUserPassword(user);
        sqlSession.commit();
        if(result >= 1){
            return true;
        }
        return false;
    }
}
