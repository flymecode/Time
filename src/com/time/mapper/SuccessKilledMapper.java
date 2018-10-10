package com.time.mapper;

import com.time.pojo.SuccessKilled;
import org.apache.ibatis.annotations.Param;


public interface SuccessKilledMapper {

    /**
     * 插入购买明细,可过滤重复
     * @param seckillId
     * @param userId
     * @return
     * 插入的行数
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userId") long userId);

    /**
     * 根据id查询SuccessKilled并携带秒杀产品对象实体
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userId") Integer userId);

}
