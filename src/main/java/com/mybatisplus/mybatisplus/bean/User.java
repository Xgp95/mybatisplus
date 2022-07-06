package com.mybatisplus.mybatisplus.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: Xugp
 * @date: 2022/7/5 10:23
 * @description:
 */
@Data
//@AllArgsConstructor
@TableName("user")
public class User {
    @TableId(value = "id",type = IdType.AUTO)
    private Long uId;
    @TableField(value = "name")
    private String userName;
    private Integer age;
    private String email;
    @TableLogic
    private int isDelete;
}
