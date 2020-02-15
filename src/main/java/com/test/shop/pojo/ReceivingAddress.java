package com.test.shop.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
@TableName("receiving_address")
public class ReceivingAddress {
    private Integer id;
    @TableField(value = "user_id")
    private Integer userId;
    private String province;
    private String city;
    private String detailed;
    private String name;
    private String phone;
}
