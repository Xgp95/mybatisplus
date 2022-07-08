package com.mybatisplus.mybatisplus.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mybatisplus.mybatisplus.bean.User;
import com.mybatisplus.mybatisplus.mapper.UserMapper;
import com.mybatisplus.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author: Xugp
 * @date: 2022/7/7 10:18
 * @description:
 */

@Service
@DS("slave_1")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
