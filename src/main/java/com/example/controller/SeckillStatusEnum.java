package com.example.controller;

import lombok.Getter;

import java.util.Arrays;

/**
 * @className: SeckillStatusEnum
 * @description
 * @author: luffy
 * @date: 2020/4/2 18:59
 * @version:V1.0
 */
public enum  SeckillStatusEnum {

    DEFAULT(100,"默认"),SUCCESS(101,"秒杀成功"),REPEAT(102,"不能重复秒杀"),FAIL(103,"秒杀失败-库存不足");
    @Getter private Integer code;
    @Getter private String status;

    SeckillStatusEnum(Integer code, String status) {
        this.code = code;
        this.status = status;
    }


    public static SeckillStatusEnum getEnumByCode(Integer code){
        return Arrays.stream(SeckillStatusEnum.values())
                .filter(e->e.code.equals(code))
                .findFirst().orElse(DEFAULT);
    }
}
