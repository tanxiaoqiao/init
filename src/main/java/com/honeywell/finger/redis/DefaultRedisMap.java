package com.honeywell.finger.redis;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Redis-HashMap操作类
 */
public class DefaultRedisMap implements RedisMap {

    private RedisTemplate redisTemplate;

    private String hashKey;
    private HashOperations operations;

    public DefaultRedisMap(RedisTemplate redisTemplate, String hashKey) {
        this.hashKey = hashKey;
        this.redisTemplate = redisTemplate;
        operations = redisTemplate.opsForHash();
    }

    @Override
    public void put(String key, Object value) {
        operations.put(hashKey, key, value);
    }

    @Override
    public Object get(String key) {
        return operations.get(hashKey, key);
    }

    @Override
    public Long remove(Object... key) {
        return operations.delete(hashKey, key);
    }

    @Override
    public Long removeAll() {
        Boolean result = redisTemplate.delete(hashKey);
        return result == true ? 1L : 0;
    }

    @Override
    public boolean exist(String key) {
        return operations.hasKey(hashKey,key);
    }

    @Override
    public void putSetTime(String key, Object value) {
        this.put(key, value);
        redisTemplate.expire(key, 1, TimeUnit.MINUTES);
    }
}
