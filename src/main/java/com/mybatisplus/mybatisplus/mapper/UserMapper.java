package com.mybatisplus.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mybatisplus.mybatisplus.bean.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author: Xugp
 * @date: 2022/7/5 10:22
 * @description:
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    Map<String,Object> selectMapById(long id);
}
