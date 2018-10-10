package com.time.service;

import com.time.dto.Exposer;
import com.time.dto.SeckillExecution;
import com.time.mapper.SeckillMapper;
import com.time.mapper.SuccessKilledMapper;
import com.time.pojo.Seckill;
import com.time.service.impl.SeckillServiceImpl;
import com.time.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SeckillServiceTest {

    @Test
    public void getSeckillList() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        SeckillMapper seckillMapper = sqlSession.getMapper(SeckillMapper.class);
        SuccessKilledMapper successKilledMapper = sqlSession.getMapper(SuccessKilledMapper.class);
        List<Seckill> seckills = seckillMapper.queryAll(0, 4);
        System.out.println(Arrays.toString(seckills.toArray()));
    }
    @Test
    public void getById() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        SeckillMapper seckillMapper = sqlSession.getMapper(SeckillMapper.class);
        SuccessKilledMapper successKilledMapper = sqlSession.getMapper(SuccessKilledMapper.class);
        Seckill seckill = seckillMapper.queryById(1);
        System.out.println(seckill);
    }
    @Test
    public void exportSeckillUrl() {
      SeckillService seckillService = new SeckillServiceImpl();
      Exposer exposer = seckillService.exportSeckillUrl(1);
      System.out.println(exposer);

    }
    @Test
    public void executeSeckill() {
        SeckillService seckillService = new SeckillServiceImpl();
        SeckillExecution b = seckillService.executeSeckill(1, 11, "fc3af3c792696e771124ff8e3508ac2b");
        System.out.println(b);
    }
    @Test
    public void reduct() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        SeckillMapper seckillMapper = sqlSession.getMapper(SeckillMapper.class);
        SuccessKilledMapper successKilledMapper = sqlSession.getMapper(SuccessKilledMapper.class);
        int i = seckillMapper.reduceNumber(1, new Date());
        System.out.println(i);
    }

}