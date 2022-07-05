package com.mybatisplus.mybatisplus;

import com.mybatisplus.mybatisplus.bean.User;
import com.mybatisplus.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisplusApplicationTests {

    @Autowired
    UserMapper userMapper;

    User user = new User();
    @Test
    void contextLoads() {
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    void testInsert() {
        user.setName("lisi");
        user.setAge(38);
        user.setEmail("zs@123.com");
        final int insert = userMapper.insert(user);
        System.out.println(insert);
        System.out.println(user.getId());
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    void testDelete() {
        user.setId(1544220501914718210l);
        userMapper.deleteById(user);
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", 18);
        userMapper.deleteByMap(map);
        List<Long> ids = Arrays.asList(1544222576732069890l, 1544223134901719041l, 1544223249867534338l);
        int deleteBatchIds = userMapper.deleteBatchIds(ids);
        System.out.println(deleteBatchIds);
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    void testUpdate() {
        user.setId(1544224248137056257l);
        user.setName("zs");
        user.setAge(18);
        user.setEmail("zs@123.com");
        userMapper.updateById(user);
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    void testQuery() {
        List<Integer> ids = Arrays.asList(1, 2, 3, 14);
        List<User> userList = userMapper.selectBatchIds(ids);
        userList.forEach(System.out::println);
        HashMap<String, Object> map = new HashMap<>();
//        map.put("name", "zhangsan");
        map.put("age", 18);
        List<User> users = userMapper.selectByMap(map);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }

    @Test
    void testMyQuery() {
        Map<String, Object> map = userMapper.selectMapById(1);
        System.out.println(map);
    }
}
