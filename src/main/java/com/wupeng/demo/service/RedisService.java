package com.wupeng.demo.service;


import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    public void set(String key, Object value) {
        //更改在redis里面查看key编码问题
        RedisSerializer redisSerializer =new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        vo.set(key, value);
    }

    /**
     * 设置过期时间<br>
     *  timeout:设置多少秒,unit是时间单位（年月日时分秒，具体可看此类）
     * @param key
     * @param value
     * @param timeout
     * @param unit
     * */
    public void set(String key, Object value, Long timeout, TimeUnit unit){
        //更改在redis里面查看key编码问题
        RedisSerializer redisSerializer =new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        //redisTemplate.opsForValue().set("key","value",5l,TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(key,value,timeout,unit);
    }

    public Object get(String key) {
        ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        return vo.get(key);
    }

    /**
     *  获取key键对应的值
     * */
    public Long getSize(String key){
        return  redisTemplate.opsForValue().size(key);
    }


    /**
     * 新增hashMap值
     * */
    public  void put(String key,Object hashKey,Object value){
        //更改在redis里面查看key编码问题
        RedisSerializer redisSerializer =new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key,hashKey,value);
    }

    /**
     * 以map集合的形式添加键值对
     * */
    public void putAll(String key,  Map<? extends Object,? extends Object>  map){
        //更改在redis里面查看key编码问题
        RedisSerializer redisSerializer =new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.opsForHash().putAll(key,map);
    }

    /**
     * 删除变量中的键值对，可以传入多个参数，删除多个键值对
     * */
    public  void delete(String key,Object... hashKeys){
        redisTemplate.opsForHash().delete(key,hashKeys);
    }

    /**
     * 获取指定key中的hashMap值
     * */
    public  Object values(String key){
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        return  hashOperations.values(key);
    }

    /**
     *  获取key中的键值对
     * */
    public  Object entries(String key){
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        return  hashOperations.entries(key);
    }

    /**
     *  获取key键对应的值
     * */
    public  Object get(String key,Object hashKey){
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        return  hashOperations.hasKey(key,hashKey);
    }

    /**
     *  获取key键对应的值（hash）
     * */
    public Long size(String key){
        return redisTemplate.opsForHash().size(key);
    }





}
