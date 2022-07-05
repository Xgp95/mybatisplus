package com.mybatisplus.mybatisplus.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: Xugp
 * @date: 2022/7/5 10:23
 * @description:
 */
@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
