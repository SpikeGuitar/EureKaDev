package com.example.spike.controller;

import com.example.spike.PoJo.Admin;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Set;

@RestController
public class TestController {

    @Inject
    private RedisTemplate redisTemplate;

    /**
     * redis获取hash格式数据方法
     *
     * @param admin
     * @return adminResult
     */
    @PostMapping("/getRedis")
    public Object getRedis(@RequestBody Admin admin){
        // key为redis键值
        String key = "admin";
        // admin.getName()为hash的key值
        Object adminResult = redisTemplate.opsForHash().get(key,admin.getName());
        return adminResult;
    }

    /**
     * redis hash格式存储数据的方法
     *
     * @param admin
     * @return OK
     */
    @PostMapping("/addRedis")
    public String addRedis(@RequestBody Admin admin){
        // key为redis键值
        String key = "admin";
        // admin.getName()为hash的key值  admin为存储进redis的数据对象
        redisTemplate.opsForHash().put(key,admin.getName(), admin);
        return "OK";
    }

    /**
     * redis 存储ZSet格式数据
     *
     * @param admin
     * @return Ok
     */
    @PostMapping("/addRedisZSet")
    public String addRedisZSet(@RequestBody Admin admin){
        // key为redis键值
        String key = "ZSet";
        // admin为存储的数据  admin.getScore()为ZSet的分值（Score）
        redisTemplate.opsForZSet().add(key ,admin, admin.getScore());
        return "OK";
    }

    /**
     * 获取redis ZSet的方法
     *
     * @param admin
     * @return adminSet
     */
    @PostMapping("/getRedisZSet")
    public Set<Admin> getRedisZSet(@RequestBody Admin admin){
        // key为redis键值
        String key = "ZSet";
        // 0为开始的分值（score） admin.getScore()为结束的分值
        Set<Admin> adminSet = redisTemplate.opsForZSet().rangeByScore(key,0,(long)admin.getScore());
        return adminSet;
    }
}
