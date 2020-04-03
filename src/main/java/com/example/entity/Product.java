package com.example.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (Product)实体类
 *
 * @author makejava
 * @since 2020-04-02 17:14:28
 */
@Data
@Accessors(chain = true)
public class Product implements Serializable {
    private static final long serialVersionUID = -11630347061307475L;
    
    private Integer id;
    
    private String  name;
    
    private Integer stock;


    public Product() {
        super();
    }

    public Product(Integer id, String name, Integer stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }
}