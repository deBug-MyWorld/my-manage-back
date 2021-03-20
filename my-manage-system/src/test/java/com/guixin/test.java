package com.guixin;

import com.guixin.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@SpringBootTest
public class test {
    @Autowired
    private UserMapper userMapper;
    @Test
    void  test1(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "123456";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        // $2a$10$ltpZqfODKvzWLEP73aZPHuX.9jiEn0jWkrOjj3EmXLO48awnAeLkG
        // $2a$10$z7PKn/lLFyIBauk6ypgsJ.feqnc0K6h4pomT6LcLZ4cEeLNeKr9.i
        System.out.println(encodedPassword);
        System.out.println(passwordEncoder.matches(rawPassword,encodedPassword));
    }
    @Test
    public void testSelectByBatchIds(){
        HashMap<String , Object> map = new HashMap<>();
        map.put("username","admin");
        userMapper.selectByMap(map);
//        List<User> users = userMapper.selectList(null);
//        users.forEach(System.out::println);
    }
    @Test
    public void testcontains(){
        List<String>list = new ArrayList<>();
        list.add("/webjars/**");
        System.out.println(list.contains("/webjars/aa"));
    }
}
