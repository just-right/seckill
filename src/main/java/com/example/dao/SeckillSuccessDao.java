package com.example.dao;

import com.example.entity.SeckillSuccess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (SeckillSuccess)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-02 17:15:44
 */
@Mapper
@Repository
public interface SeckillSuccessDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SeckillSuccess queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SeckillSuccess> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param seckillSuccess 实例对象
     * @return 对象列表
     */
    List<SeckillSuccess> queryAll(SeckillSuccess seckillSuccess);

    /**
     * 新增数据
     *
     * @param seckillSuccess 实例对象
     * @return 影响行数
     */
    int insert(SeckillSuccess seckillSuccess);

    /**
     * 修改数据
     *
     * @param seckillSuccess 实例对象
     * @return 影响行数
     */
    int update(SeckillSuccess seckillSuccess);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}