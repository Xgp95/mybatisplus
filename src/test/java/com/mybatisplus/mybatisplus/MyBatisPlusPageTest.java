package com.mybatisplus.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatisplus.mybatisplus.bean.User;
import com.mybatisplus.mybatisplus.mapper.UserMapper;
import com.mybatisplus.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * @author: Xugp
 * @date: 2022/7/7 15:42
 * @description:
 */
@SpringBootTest
public class MyBatisPlusPageTest {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Test
    void bigInsert() {
        List users = new LinkedList<User>();
        for (int i = 0; i < 1000; i++) {
            User user = new User();
            String substring = UUID.randomUUID().toString().replace("-", "").substring(1, 5);
            int age = 10 + (new Random().nextInt(50));
//            user.setUserName(substring);
            user.setAge(age);
            user.setEmail(substring + "@123.com");
//            System.out.println(user);
            users.add(user);
        }
//        System.out.println("!!!!!!!!!!!!!");
//        users.forEach(System.out::println);
        userService.saveBatch(users);
    }

    @Test
    void deleteIdGe() {
        userService.remove(new QueryWrapper<User>().ge("id", 17));
    }

    @Test
    void pageTest() {
        Page page = new Page<User>(-1, 5);
        userService.page(page);
        List records = page.getRecords();
        for (Object record : records) {
            System.out.println(record + "***");
        }
    }

    @Test
    void myPageTest() {
        Page page = new Page<User>(1, 5);
        userMapper.selectPageAgeGe(page, 35);
        List records = page.getRecords();
        records.forEach(System.out::println);
        System.out.println("~~~~~~~~~~~~~~~~~~");

        userMapper.selectPageAgeEq(page, 18);
        records = page.getRecords();
        records.forEach(System.out::println);

    }

}
