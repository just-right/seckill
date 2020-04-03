package com.example.entity;

import lombok.Getter;

public enum StatusCodeEnum {
    SUCCESS("Success",200),FAIL("Fail",500);
    @Getter private String description;
    @Getter private Integer status;

    StatusCodeEnum(String description, Integer status){
        this.status  = status;
        this.description = description;
    }

    public static StatusCodeEnum getStatusCodeByStatus(String description){
        for (StatusCodeEnum statusCode : StatusCodeEnum.values()){
            if (statusCode.getDescription().equals(description)){
                return statusCode;
            }
        }
        return null;
    }
}
