package com.demo.redisoperation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.BinaryJedis;

@Configuration
@ComponentScan("com.demo.redisoperation")
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String HOST;

    @Value("${spring.redis.port}")
    private String PORT;

    @Bean
    public synchronized BinaryJedis jedisInstance() {
        BinaryJedis jedis = new BinaryJedis(HOST, Integer.parseInt(PORT));
        return jedis;
    }
}
