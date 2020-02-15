package com.test.shop.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName(value = "category")
public class Category {
    private Integer id;
    private String name;
    private Integer lv;
    @TableField(value = "parent_id")
    private Integer parentId;
}
