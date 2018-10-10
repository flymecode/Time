package com.time.service;

import com.time.dto.Exposer;
import com.time.dto.SeckillExecution;
import com.time.exception.RepeatKillException;
import com.time.exception.SeckillCloseException;
import com.time.exception.SeckillException;
import com.time.pojo.Seckill;

import java.util.List;

/**
 * 业务接口:站在"使用者"角度设计接口
 * 三个方面:方法定义粒度,参数,返回类型(return 类型/异常)
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启输出秒杀接口地址,
     * 否则输出系统时间和秒杀时间
     *
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userId
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, Integer userId, String md5)
        throws SeckillException, RepeatKillException, SeckillCloseException;

}
