package com.example.spike.controller;

import com.example.spike.PoJo.Admin;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class TestController {

    @Inject
    private RedisTemplate redisTemplate;


    @PostMapping("/getRedis")
    public Object getRedis(@RequestBody Admin admin){
        Object adminResult = redisTemplate.opsForHash().get("admin",admin.getName());
        return adminResult;
    }

    @PostMapping("/addRedis")
    public String addRedis(@RequestBody Admin admin){
        redisTemplate.opsForHash().put("admin",admin.getName(), admin);
        return "OK";
    }

}
