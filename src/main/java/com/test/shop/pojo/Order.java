package com.test.shop.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Order {
    private Integer id;
    @TableField(value = "order_num")
    private String orderNum;
    private String sku;
    private double realTotalMoney;
    @TableField(value = "receiving_address_id")
    private Integer receivingAddressId;
    @TableField(value = "pay_type")
    private String payType;
    private Integer orderStatus;
    private String createTime;
    private String delayedTime;
    private String payTime;
    private Integer expressId;
    private String expressNo;
    private String deliveryTime;
    private String receiveTime;
    private ReceivingAddress receivingAddress;
}
