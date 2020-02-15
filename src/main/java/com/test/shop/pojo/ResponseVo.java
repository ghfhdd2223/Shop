package com.test.shop.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResponseVo<T> {
    private Integer code;
    private String msg;
    private T data;
}
