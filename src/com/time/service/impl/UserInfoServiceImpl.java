package com.time.service.impl;

import com.time.pojo.UserInfo;
import com.time.mapper.UserInfoMapper;
import com.time.service.UserInfoService;
import com.time.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

public class UserInfoServiceImpl implements UserInfoService {

    @Override
    public UserInfo findUserInfo(Integer id) throws SQLException {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        return userInfoMapper.findUserInfo(id);
    }
}
