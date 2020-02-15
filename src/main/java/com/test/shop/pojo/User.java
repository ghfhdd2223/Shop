package com.test.shop.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("User")
public class User {
    private Integer uId;
    private String account;
    private String password;
    private String name;
    private String phone;
    private String createTime;
}
