package com.example.service.impl;

import com.example.dao.SeckillSuccessDao;
import com.example.entity.SeckillSuccess;
import com.example.service.SeckillSuccessService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SeckillSuccess)表服务实现类
 *
 * @author makejava
 * @since 2020-04-02 17:15:44
 */
@Service("seckillSuccessService")
public class SeckillSuccessServiceImpl implements SeckillSuccessService {
    @Resource
    private SeckillSuccessDao seckillSuccessDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SeckillSuccess queryById(Integer id) {
        return this.seckillSuccessDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SeckillSuccess> queryAllByLimit(int offset, int limit) {
        return this.seckillSuccessDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param seckillSuccess 实例对象
     * @return 实例对象
     */
    @Override
    public SeckillSuccess insert(SeckillSuccess seckillSuccess) {
        this.seckillSuccessDao.insert(seckillSuccess);
        return seckillSuccess;
    }

    /**
     * 修改数据
     *
     * @param seckillSuccess 实例对象
     * @return 实例对象
     */
    @Override
    public SeckillSuccess update(SeckillSuccess seckillSuccess) {
        this.seckillSuccessDao.update(seckillSuccess);
        return this.queryById(seckillSuccess.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.seckillSuccessDao.deleteById(id) > 0;
    }
}