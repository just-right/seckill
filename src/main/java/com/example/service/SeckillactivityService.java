package com.example.service;

import com.example.controller.SeckillStatusEnum;
import com.example.entity.Seckillactivity;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * (Seckillactivity)表服务接口
 *
 * @author makejava
 * @since 2020-04-02 17:15:55
 */
public interface SeckillactivityService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Seckillactivity queryById(Integer id, Function<Integer, Seckillactivity> function);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Seckillactivity> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param seckillactivity 实例对象
     * @return 实例对象
     */
    Seckillactivity insert(Seckillactivity seckillactivity);

    /**
     * 修改数据
     *
     * @param seckillactivity 实例对象
     * @return 实例对象
     */
    Seckillactivity update(Seckillactivity seckillactivity);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 秒杀活动
     */
    SeckillStatusEnum secKill(Integer id, String telphone, BiFunction<Integer, String, SeckillStatusEnum> biFunction);

    /**
     * 开启秒杀
     */
    Seckillactivity startSecKill(Integer id, Integer num, BiFunction<Integer, Integer, Seckillactivity> biFunction);

}