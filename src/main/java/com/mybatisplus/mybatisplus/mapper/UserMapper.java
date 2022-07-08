package com.mybatisplus.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatisplus.mybatisplus.bean.User;
import org.apache.ibatis.annotations.Param;
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

    Page<User> selectPageAgeGe(@Param("page") Page<User> page,@Param("age") Integer age);

    Page<User> selectPageAgeEq(@Param("page") Page<User> page,@Param("age") Integer age);
}
