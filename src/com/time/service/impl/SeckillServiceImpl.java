package com.time.service.impl;

import com.time.dto.Exposer;
import com.time.dto.SeckillExecution;
import com.time.enums.SeckillStatEnum;
import com.time.exception.RepeatKillException;
import com.time.exception.SeckillCloseException;
import com.time.exception.SeckillException;
import com.time.mapper.SeckillMapper;
import com.time.mapper.SuccessKilledMapper;
import com.time.pojo.Seckill;
import com.time.pojo.SuccessKilled;
import com.time.service.SeckillService;
import com.time.utils.MD5Utils;
import com.time.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class SeckillServiceImpl implements SeckillService {
    // md5盐值字符串，用于混淆md5
    private final String SLAT = "hfuiasdfhoasdifhsdiaugf87sdagfhew89qryrajks";
    private Logger logger = LoggerFactory.getLogger(this.getClass());



    @Override
    public List<Seckill> getSeckillList() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        SeckillMapper seckillMapper = sqlSession.getMapper(SeckillMapper.class);
        return seckillMapper.queryAll(0, 4);
    }

    @Override
    public Seckill getById(long seckillId) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        SeckillMapper seckillMapper = sqlSession.getMapper(SeckillMapper.class);
        return seckillMapper.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        SeckillMapper seckillMapper = sqlSession.getMapper(SeckillMapper.class);
        Seckill seckill = seckillMapper.queryById(seckillId);
        if (seckill == null) {
            return new Exposer(false, seckillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        // 转化特定的字符串的过程，不可逆
        String md5 = MD5Utils.getMD5(seckillId, SLAT);
        return new Exposer(true, md5, seckillId);
    }

    @Override
    public SeckillExecution executeSeckill(long seckillId, Integer userId, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        SeckillMapper seckillMapper = sqlSession.getMapper(SeckillMapper.class);
        SuccessKilledMapper successKilledMapper = sqlSession.getMapper(SuccessKilledMapper.class);


        try {
            if (md5 == null || !md5.equals(MD5Utils.getMD5(seckillId, SLAT))) {
                throw new SeckillException("秒杀数据被篡改");
            }
            // 执行秒杀逻辑
            Date nowTime = new Date();
            int updateCount = seckillMapper.reduceNumber(seckillId, nowTime);
            if (updateCount <= 0) {
                sqlSession.rollback();
                // 没有更新记录，秒杀结束
                throw new SeckillCloseException("秒杀关闭");
            } else {
                // 记录购买行为
                int insertCount = successKilledMapper.insertSuccessKilled(seckillId, userId);
                // 唯一seckillid phone
                if (insertCount <= 0) {
                    sqlSession.rollback();
                    // 重复秒杀
                    throw new RepeatKillException("重复秒杀");
                } else {
                    // 秒杀成功
                    sqlSession.commit();
                    SuccessKilled successKilled = successKilledMapper.queryByIdWithSeckill(seckillId, userId);
                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS);
                }
            }

        } catch (SeckillCloseException e) {
            throw e;
        } catch (RepeatKillException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            // 所有编译期异常，转化为运行期异常
            throw new SeckillException("seckill inner error" + e.getMessage());
        }

    }

}
