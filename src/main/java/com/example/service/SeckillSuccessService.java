package com.example.service;

import com.example.entity.SeckillSuccess;

import java.util.List;

/**
 * (SeckillSuccess)表服务接口
 *
 * @author makejava
 * @since 2020-04-02 17:15:44
 */
public interface SeckillSuccessService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SeckillSuccess queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SeckillSuccess> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param seckillSuccess 实例对象
     * @return 实例对象
     */
    SeckillSuccess insert(SeckillSuccess seckillSuccess);

    /**
     * 修改数据
     *
     * @param seckillSuccess 实例对象
     * @return 实例对象
     */
    SeckillSuccess update(SeckillSuccess seckillSuccess);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}