package com.test.shop.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("products")
public class Products {
    private Integer id;
    private String name;
    private String sku;
    private double price;
    @TableField(value = "category_id")
    private Integer categoryId;
}
