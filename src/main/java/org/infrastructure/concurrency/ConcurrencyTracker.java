package org.infrastructure.concurrency;

import org.infrastructure.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class ConcurrencyTracker {

    private final RedisClient redisClient;

    @Autowired
    public ConcurrencyTracker(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    public boolean tryAcquireLock(String key) {
        String value = "";
        long ttl = Duration.ofMinutes(5).toSeconds();
        return redisClient.setIfAbsent(key, value, ttl);
    }

    public void releaseLock(String key) {
        redisClient.removeFromCache(key);
    }
}
