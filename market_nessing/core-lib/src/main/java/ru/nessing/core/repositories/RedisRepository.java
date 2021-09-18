package ru.nessing.core.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RedisRepository {

    @Autowired
    private RedisTemplate<String, Object> tokenRedisTemplate;

    public void putToken(String token, Duration duration) {
        tokenRedisTemplate.opsForValue().set("token:" + token, 1, duration);
    }

    public boolean checkToken(String token) {
        return tokenRedisTemplate.opsForValue().get("token:" + token) != null;
    }
}
