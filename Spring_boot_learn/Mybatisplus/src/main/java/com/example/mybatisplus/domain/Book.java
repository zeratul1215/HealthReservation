package com.example.mybatisplus.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

@Data
@TableName("tbl_book")
public class Book {
    //@TableId(type = IdType.INPUT)//用户自己输入ID
    @TableId(type = IdType.ASSIGN_ID)//雪花算法生成的ID 0|41位时间戳|十位机器码|十二位序列号
    private Integer id;
    @TableField(value = "name")//解决类和数据库名称不匹配的问题
    private String name;
    private String type;
    @TableField(select = false)//字段不参与查询
    private String description;
    @TableField(exist = false)//类中包含表中没有的属性
    private Boolean Selling;
    @TableLogic(value = "0", delval = "1")//逻辑删除
    private Integer delete;
    @Version
    private Integer version;
}
