package com.changgou.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 返回结果实体类
 */
@Data
public class Result<T> {
    private boolean flag;//是否成功
    private Integer code;//返回码
    private String message;//返回消息

    private T data;//返回数据

    public Result(){ }
    public Result(int code, String message, boolean flag) {
        this(code,message,false,null);
    }
    public Result(int code, String message, boolean flag,T data) {
        this.code = code;
        this.message = message;
        this.flag = flag;
        this.data = data;
    }
}
