package com.mybatisplus.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mybatisplus.mybatisplus.bean.User;
import com.mybatisplus.mybatisplus.mapper.UserMapper;
import com.mybatisplus.mybatisplus.service.UserService;
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

    @Autowired
    UserService userService;

    User user = new User();

    QueryWrapper<User> queryWrapper = new QueryWrapper<>();

    UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();

    LambdaUpdateWrapper<User> userLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
    LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();

    @Test
    void contextLoads() {
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    void testInsert() {
        user.setUserName("lisi");
        user.setAge(38);
        user.setEmail("zs@123.com");
        int insert = userMapper.insert(user);
//        System.out.println(insert);
        System.out.println(user.getUId());
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    void testDelete() {
//        user.setId(1544220501914718210l);
        user.setUId(6l);
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
//        user.setId(1544224248137056257l);
        user.setUserName("zs");
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

    @Test
    void testWrapper() {
//        查询用户名包含a 年龄20到30 邮箱不是null的用户
        queryWrapper.like("name", "a")
                .between("age", 20, 30)
                .isNotNull("email");

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
        System.out.println("#################");
    }

    @Test
    void testWrapper1() {
        //        按年龄降序查询 年龄相同id升序
        queryWrapper.orderByDesc("age")
                .orderByAsc("id");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
        System.out.println("#################");
    }

    @Test
    void testWrapper2() {
//        删除email是NULL id是7-10的用户
        queryWrapper.isNull("email")
                .between("id", 7, 10);
        int deleteCount = userMapper.delete(queryWrapper);
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    void testWrapper3() {
//        把年龄大于20且名字中有a 或者 邮箱是null的用户修改age为18 email为default@123.com
        queryWrapper.ge("age", 20)
                .like("name", "a")
                .or()
                .isNull("email");
        user.setAge(18);
        user.setEmail("default@123.com");
        int update = userMapper.update(user, queryWrapper);
        System.out.println("修改了" + update + "个");
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    void testWrapper4() {
//        把年龄大于20且(名字中有a 或者 邮箱是null)的用户修改age为18 email为default@123.com
        queryWrapper.gt("age", 20)
                .and(i -> i.like("name", "a").or().isNull("email"));
        user.setAge(18);
        user.setEmail("default@123.com");
        int update = userMapper.update(user, queryWrapper);
        System.out.println("修改了" + update + "个");
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    void testWrapper5() {
//        查询 name age id字段
        queryWrapper.select("name","age","id");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
    }

    @Test
    void testWrapper6() {
//        查询id>=3的用户
//        queryWrapper.ge("id", 3);
//        List<User> userList = userMapper.selectList(queryWrapper);
//        for (User user1 : userList) {
//            System.out.println(user1);
//        }

//        查询id>3的用户
        queryWrapper.inSql("id", "select id from user where id > 3");
        List<User> userList = userMapper.selectList(queryWrapper);
        for (User user1 : userList) {
            System.out.println(user1);
        }
    }

    @Test
    void testServiceImplSave() {
        user.setUserName("zs");
        user.setAge(22);
        user.setEmail("zs@123.com");
        userService.save(user);
        queryWrapper.like("name", "z");
        List<User> userList = userService.list(queryWrapper);
        for (User user1 : userList) {
            System.out.println(user1);
        }
    }

    @Test
    void testServiceImplRemove() {
        queryWrapper.gt("age", 30);
        userService.remove(queryWrapper);
        userService.list().forEach(System.out::println);
    }

    @Test
    void testServiceImplUpdate() {
        user.setUserName("zhangsan");
        user.setAge(22);
        user.setEmail("zhangsan@123.com");
//        queryWrapper.like("name", "z");
//        userService.saveOrUpdate(user,queryWrapper);

//        queryWrapper.in("id", 14,15);
//        userService.update(user, queryWrapper);

//        updateWrapper.set("name", "zs")
//                .set("email", "zs@163.com")
//                .like("email", "z");

        userLambdaUpdateWrapper.set(User::getUserName,"ww")
                .set(User::getEmail, "ww@163.com")
                .set(User::getAge,26)
//                .like(User::getEmail, "")
                .and(i -> i.eq(User::getUId, 14));
        userService.update(userLambdaUpdateWrapper);
        List<User> userList = userService.list();
        for (User user1 : userList) {
            System.out.println(user1);
        }
    }

    @Test
    void testServiceImplQuery() {
    }
}
