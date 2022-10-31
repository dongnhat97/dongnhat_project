//package com.java.utils;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * [OVERVIEW] RedisCommonUtils
// *
// * @author: BAP(NhatDV)
// * @version: 1.0
// * @History [NUMBER] [VER] [DATE] [USER] [CONTENT]
// * -----------------------------------------
// * 001 1.0 2021/12/21 BAP(NhatDV) Create new
// */
//@Component
//public class RedisCommonUtils {
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    /**
//     * function set key,value for redis
//     *
//     * @param key
//     * @param value
//     */
//    public void setRedis(String key, String value) {
//        stringRedisTemplate.opsForValue().set(key, value);
//    }
//
//    /**
//     * function get value for redis
//     *
//     * @param key
//     * @return String
//     */
//    public String getRedis(String key) {
//        if (key == null || key.isEmpty() || key.trim().length() == 0) {
//            return null;
//        }
//        return stringRedisTemplate.opsForValue().get(key);
//    }
//
//    /**
//     * function set key,value for hset in redis
//     *
//     * @param key
//     * @param field
//     * @param value
//     */
//    public void setHsetRedis(String key, String field, String value) {
//        stringRedisTemplate.opsForHash().put(key, field, value);
//    }
//
//    /**
//     * function get value for hset in redis
//     *
//     * @param key
//     * @return list<value> of key
//     */
//    public List<Object> getHsetRedis(String key) {
//        return stringRedisTemplate.opsForHash().values(key);
//    }
//
//    /**
//     * function get value from key,field for hset in redis
//     *
//     * @param key
//     * @param field
//     * @return String
//     */
//    public String getHsetRedis(String key, String field) {
//        return stringRedisTemplate.opsForHash().get(key, field).toString();
//    }
//
//    /**
//     * function set key,map<filed,value> for hset in redis
//     *
//     * @param key
//     * @param mapFieldValue
//     */
//    public void setMapHsetRedis(String key, Map<String, String> mapFieldValue) {
//        stringRedisTemplate.opsForHash().putAll(key, mapFieldValue);
//    }
//}