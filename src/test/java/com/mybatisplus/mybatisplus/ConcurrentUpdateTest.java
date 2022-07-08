package com.mybatisplus.mybatisplus;

import com.mybatisplus.mybatisplus.bean.Product;
import com.mybatisplus.mybatisplus.bean.User;
import com.mybatisplus.mybatisplus.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Xugp
 * @date: 2022/7/7 17:11
 * @description:
 */
@SpringBootTest
public class ConcurrentUpdateTest {
    @Autowired
    ProductMapper productMapper;
    @Test
    void concurrentUpdateTest() {
        Product p1 = productMapper.selectById(1);
        Product p2 = productMapper.selectById(1);

        p1.setPrice(p1.getPrice() + 50);
        int i = productMapper.updateById(p1);
        while (i == 0) {
            p1 = productMapper.selectById(1);
            p1.setPrice(p1.getPrice() + 50);
            i = productMapper.updateById(p1);
        }

        p2.setPrice(p2.getPrice() - 30);
        int j = productMapper.updateById(p2);
        while (j == 0) {
            p2 = productMapper.selectById(1);
            p2.setPrice(p1.getPrice() - 30);
            j = productMapper.updateById(p2);
        }
        Product p = productMapper.selectById(1);
        System.out.println(p.getPrice());
    }
}
