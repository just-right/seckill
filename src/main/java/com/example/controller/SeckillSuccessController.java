package com.example.controller;

import com.example.entity.SeckillSuccess;
import com.example.service.SeckillSuccessService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (SeckillSuccess)表控制层
 *
 * @author makejava
 * @since 2020-04-02 17:15:44
 */
@RestController
@RequestMapping("seckillSuccess")
public class SeckillSuccessController {
    /**
     * 服务对象
     */
    @Resource
    private SeckillSuccessService seckillSuccessService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SeckillSuccess selectOne(Integer id) {
        return this.seckillSuccessService.queryById(id);
    }

}