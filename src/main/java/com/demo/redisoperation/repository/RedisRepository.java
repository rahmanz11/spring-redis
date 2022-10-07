package com.demo.redisoperation.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.*;

@Repository
public class RedisRepository {

    private RedisTemplate redislab;

    public RedisRepository(RedisTemplate redisTemplate) {
        this.redislab = redisTemplate;
    }

    public void add(final String key, final String value) {
        redislab.opsForList().rightPush(key.toUpperCase(), value);
    }

    public List<String> get(final String key) {
        return redislab.opsForList().range(key.toUpperCase(), 0, -1);
    }

    public boolean delete(final String key) {
        return redislab.delete(key.toUpperCase());
    }

    public boolean remove(final String key, final String value) {
        Long count = redislab.opsForList().remove(key.toUpperCase(), -1, value);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
}
