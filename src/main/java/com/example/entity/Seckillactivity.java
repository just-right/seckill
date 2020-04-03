package com.example.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (Seckillactivity)实体类
 *
 * @author makejava
 * @since 2020-04-02 17:15:55
 */
//@Builder
@Data
@Accessors(chain = true)
public class Seckillactivity implements Serializable {
    private static final long serialVersionUID = 200751732770014719L;
    
    private Integer id;
    
    private Integer product;
    
    private Integer seckillnum;
    
    private String activityname;

    public Seckillactivity() {
        super();
    }

    public Seckillactivity(Integer id, Integer product, Integer seckillnum, String activityname) {
        this.id = id;
        this.product = product;
        this.seckillnum = seckillnum;
        this.activityname = activityname;
    }
}