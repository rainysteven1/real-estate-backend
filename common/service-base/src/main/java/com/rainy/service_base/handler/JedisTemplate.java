package com.rainy.service_base.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisTemplate {
    @Autowired
    private JedisPool jedisPool;

    /**
     * 获取Jedis资源
     */
    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * 释放Jedis连接
     */
    public void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
