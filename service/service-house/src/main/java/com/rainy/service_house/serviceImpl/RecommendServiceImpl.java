package com.rainy.service_house.serviceImpl;

import com.google.common.collect.Lists;
import com.rainy.service_base.handler.JedisTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RecommendServiceImpl {
    @Autowired
    private JedisTemplate jedisTemplate;

    @Value("${redis.key}")
    private String hotHouseKey;

    public void increase(Long id) {
        Jedis jedis = jedisTemplate.getJedis();
        jedis.zincrby(hotHouseKey, 1.0D, String.valueOf(id));
        jedis.zremrangeByRank(hotHouseKey, 0, -11);
        jedis.close();
    }

    public List<Long> getHot() {
        try {
            Jedis jedis = jedisTemplate.getJedis();
            Set<String> idSet = jedis.zrevrange(hotHouseKey, 0, -1);
            jedis.close();
            return idSet.stream().map(Long::parseLong).collect(Collectors.toList());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Lists.newArrayList();
        }
    }
}
