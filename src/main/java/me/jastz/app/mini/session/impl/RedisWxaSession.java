package me.jastz.app.mini.session.impl;

import me.jastz.app.mini.session.Session;
import com.google.common.collect.Maps;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author zhiwen
 */
public class RedisWxaSession<V> implements Session<V> {

    private RedisTemplate<String, V> redisTemplate;

    public RedisWxaSession(RedisTemplate<String, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void put(String key, V value) {
        redisTemplate.opsForValue().set(key, value, 60 * 24 * 7L, TimeUnit.MINUTES);
    }

    @Override
    public void put(String key, V value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    @Override
    public V get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Map<String, V> allValue(String keyPrefix) {
        Map<String, V> map = Maps.newHashMap();
        Set<String> sessionKeys = redisTemplate.keys(String.format(keyPrefix, "*"));
        sessionKeys.forEach(key -> map.put(key, redisTemplate.opsForValue().get(key)));
        return map;
    }

    @Override
    public void removeByKey(String key) {
        redisTemplate.delete(key);
    }
}
