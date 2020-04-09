package com.example.service.util;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.example.controller.SeckillStatusEnum;
import com.example.dao.SeckillSuccessDao;
import com.example.dao.SeckillactivityDao;
import com.example.entity.SeckillSuccess;
import com.example.entity.Seckillactivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @className: SeckillUtils
 * @description
 * @author: luffy
 * @date: 2020/4/2 18:38
 * @version:V1.0
 */
@Component
public class SeckillUtils {
    private static SeckillactivityDao seckillactivityDao1;
    private static SeckillSuccessDao seckillSuccessDao1;
    private static JedisPool jedisPool1;
    private static Lock lock = new ReentrantLock();
    private static RuntimeSchema<Seckillactivity> schema = RuntimeSchema.createFrom(Seckillactivity.class);

    @Autowired
    private SeckillactivityDao seckillactivityDao2;

    @Autowired
    private JedisPool jedisPool2;

    @Autowired
    private SeckillSuccessDao seckillSuccessDao2;

    @PostConstruct
    private void init() {
        seckillactivityDao1 = seckillactivityDao2;
        jedisPool1 = jedisPool2;
        seckillSuccessDao1 = seckillSuccessDao2;
    }

    public static Seckillactivity getSeckill(Integer id) {

        try (Jedis jedis = jedisPool1.getResource()) {
            String key = "seckill:" + id + ":1024";
            //从缓存获取活动详情数据
            byte[] activityDetail = jedis.get(key.getBytes());
            if (activityDetail != null && activityDetail.length > 0) {
                Seckillactivity seckillactivity = schema.newMessage();
                ProtostuffIOUtil.mergeFrom(activityDetail, seckillactivity, schema);
                return seckillactivity;
            } else {
                //缓存无-从数据库获取-存入缓存-设置过期时间
                Seckillactivity activity = seckillactivityDao1.queryById(id);
                Optional<Seckillactivity> optional = Optional.ofNullable(activity);
                Seckillactivity seckillactivity = optional.orElse(new Seckillactivity());

                byte[] bytes = ProtostuffIOUtil.toByteArray(seckillactivity, schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //超时缓存，1小时
                int timeout = 60 * 60;
                String result = jedis.setex(key.getBytes(), timeout, bytes);

                return seckillactivity;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * static 方法是类级别的，所以加锁也是锁整个方法
     *
     * @param id       活动ID
     * @param telphone
     * @return
     */
    @Transactional
    public static  SeckillStatusEnum doSecKill(Integer id, String telphone) {

        lock.lock();
        String luaScript = "local productStockKey='produt:'..KEYS[1]..\":stock\";\r\n" +
                "local key='seckill:'..KEYS[1]..\":success\";\r\n" +
                "local value='seckill:'..KEYS[2]..\":success\";\r\n" +
                "local userExist = redis.call(\"sismember\",key,value)" +
                "if tonumber(userExist) == 1 then \r\n" +
                "   return 102; \r\n" +
                "end  \r\n" +
                "local num = redis.call(\"get\",productStockKey);\r\n" +
                "if tonumber(num)<=0 then \r\n" +
                "   return 103;\r\n" +
                "else \r\n" +
                "   redis.call(\"decr\",productStockKey);\r\n" +
                "   redis.call(\"sadd\",key,value)\r\n" +
                "end\r\n" +
                "return 101";

        try (Jedis jedis = jedisPool1.getResource()) {


            Seckillactivity seckillactivity = seckillactivityDao1.queryById(id);

            Long stausCode = (Long) jedis.eval(luaScript, 2, String.valueOf(id), telphone);
            if (stausCode.equals(101L)) {

                //减库存
                Integer productNum = seckillactivity.getSeckillnum();

                seckillactivity.setSeckillnum(productNum.intValue() - 1);
                seckillactivityDao1.update(seckillactivity);

                //插入秒杀成功记录
                SeckillSuccess seckillSuccess = new SeckillSuccess();
                seckillSuccess.setTelphone(telphone)
                        .setActivity(id)
                        .setProduct(seckillactivity.getProduct());
                seckillSuccessDao1.insert(seckillSuccess);
            }

            return SeckillStatusEnum.getEnumByCode(Integer.parseInt(stausCode.toString()));


        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
        return SeckillStatusEnum.getEnumByCode(100);

    }

//        String productStockKey = "produt:"+id+":stock";
//        String key = "seckill:"+id+":success";
//        String value = "seckill:"+telphone+":success";
//        if(jedis.sismember(key, value)){
//            return SeckillStatusEnum.getEnumByCode(102);
//        }else {
//            //开始秒杀-库存遗留
//            jedis.watch(key);
//            Transaction transaction =  jedis.multi();
//            if(Integer.parseInt(jedis.get(productStockKey)) > 0 ){
//                jedis.decr(productStockKey);
//                jedis.sadd(key,value);
//            }else {
//                return SeckillStatusEnum.getEnumByCode(103);
//            }
//            transaction.exec();
//        }
//
//        jedis.close();
//
//        return SeckillStatusEnum.getEnumByCode(101);
//    }

    /**
     * @param id  商品ID
     * @param num
     * @return
     */
    public static Seckillactivity startSeckill(Integer id, Integer num) {
        //新增秒杀活动
        Seckillactivity seckillactivity = new Seckillactivity();
        seckillactivity.setProduct(id)
                .setSeckillnum(num)
                .setActivityname("薅羊毛-" + UUID.randomUUID().toString().substring(0, 5));
        seckillactivityDao1.insert(seckillactivity);

        //将秒杀活动库存-存入redis
        String productStockKey = "produt:" + seckillactivity.getId() + ":stock";
        try (Jedis jedis = jedisPool1.getResource();) {
            jedis.set(productStockKey, String.valueOf(num));
        }
        return seckillactivity;
    }
}
