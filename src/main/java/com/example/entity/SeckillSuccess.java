package com.example.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (SeckillSuccess)实体类
 *
 * @author makejava
 * @since 2020-04-02 17:15:44
 */
@Data
@Accessors(chain = true)
public class SeckillSuccess implements Serializable {
    private static final long serialVersionUID = 451041926767437687L;
    
    private Integer id;
    
    private String telphone;
    
    private Integer activity;
    
    private Integer product;

    public SeckillSuccess() {
        super();
    }

    public SeckillSuccess(Integer id, String telphone, Integer activity, Integer product) {
        this.id = id;
        this.telphone = telphone;
        this.activity = activity;
        this.product = product;
    }
}