package com.example.service.impl;

import com.example.controller.SeckillStatusEnum;
import com.example.dao.SeckillactivityDao;
import com.example.entity.Seckillactivity;
import com.example.service.SeckillactivityService;
import com.example.service.util.SeckillUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * (Seckillactivity)表服务实现类
 *
 * @author makejava
 * @since 2020-04-02 17:15:55
 */
@Service("seckillactivityService")
public class SeckillactivityServiceImpl implements SeckillactivityService {
    @Resource
    private SeckillactivityDao seckillactivityDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Seckillactivity queryById(Integer id, Function<Integer,Seckillactivity> function) {
        return function.apply(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Seckillactivity> queryAllByLimit(int offset, int limit) {
        return this.seckillactivityDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param seckillactivity 实例对象
     * @return 实例对象
     */
    @Override
    public Seckillactivity insert(Seckillactivity seckillactivity) {
        this.seckillactivityDao.insert(seckillactivity);
        return seckillactivity;
    }

    /**
     * 修改数据
     *
     * @param seckillactivity 实例对象
     * @return 实例对象
     */
    @Override
    public Seckillactivity update(Seckillactivity seckillactivity) {
        this.seckillactivityDao.update(seckillactivity);
        return this.queryById(seckillactivity.getId(), SeckillUtils::getSeckill);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.seckillactivityDao.deleteById(id) > 0;
    }

    @Override
    public SeckillStatusEnum secKill(Integer id, String telphone, BiFunction<Integer,String,SeckillStatusEnum> biFunction) {

        return biFunction.apply(id,telphone);

    }

    @Override
    public Seckillactivity startSecKill(Integer id,Integer num, BiFunction<Integer,Integer,Seckillactivity> biFunction) {
        return biFunction.apply(id,num);
    }
}