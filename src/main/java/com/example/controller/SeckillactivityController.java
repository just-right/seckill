package com.example.controller;

import com.example.entity.Seckillactivity;
import com.example.service.SeckillactivityService;
import com.example.service.util.SeckillUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

/**
 * (Seckillactivity)表控制层
 *
 * @author makejava
 * @since 2020-04-02 17:15:55
 */
@RestController
@RequestMapping("/seckillactivity")
public class SeckillactivityController {
    /**
     * 服务对象
     */
    @Resource
    private SeckillactivityService seckillactivityService;

    /**
     * 查看秒杀活动
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne/{id}")
    public Map<String,Object> selectOne(@PathVariable("id") Integer id) {
        Map<String, Object> resMap = new HashMap<>();
        Seckillactivity seckillactivity = this.seckillactivityService.queryById(id, SeckillUtils::getSeckill);
        resMap.put("status", SeckillStatusEnum.getEnumByCode(100).getStatus());
        resMap.put("data", seckillactivity);
        return resMap;
    }

    /**
     * 进行秒杀
     * @param id
     * @param telphone
     * @return
     */
    @PostMapping("/seckill/{id}")
    public Map<String,Object> secKill(@PathVariable("id") Integer id, @PathParam("telphone") String telphone){
        Map<String, Object> resMap = new HashMap<>();

        SeckillStatusEnum seckillStatusEnum = this.seckillactivityService.secKill(id,telphone,SeckillUtils::doSecKill);
        resMap.put("data", seckillStatusEnum);

        return resMap;
    }

    /**
     * 开启秒杀活动
     * @param id
     * @param num
     * @return
     */
    @GetMapping("/seckill/start")
    public Map<String,Object> secKillStart(@PathParam("id") Integer id,@PathParam("num")Integer num){
        Map<String, Object> resMap = new HashMap<>();

        Seckillactivity seckillactivity =  this.seckillactivityService.startSecKill(id,num,SeckillUtils::startSeckill);
        resMap.put("data", seckillactivity);

        return resMap;
    }

}