package com.mybatisplus.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mybatisplus.mybatisplus.bean.User;
import com.mybatisplus.mybatisplus.service.UserService;
import com.mybatisplus.mybatisplus.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author: Xugp
 * @date: 2022/7/8 15:39
 * @description:
 */
@SpringBootTest
public class DynamicDatasourceTest {
    @Autowired
    UserService userService;
    @Test
    void dynamicDatasourceTest() {

        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.ge(User::getUId, 2);
        List<User> userList = userService.list(userLambdaQueryWrapper);
        userList.forEach(System.out::println);
    }

}
