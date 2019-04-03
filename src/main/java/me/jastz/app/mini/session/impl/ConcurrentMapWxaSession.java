package me.jastz.app.mini.session.impl;

import me.jastz.app.mini.session.Session;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author zhiwen
 */
public class ConcurrentMapWxaSession<V> implements Session<V> {
    private final ConcurrentHashMap concurrentMap = new ConcurrentHashMap();

    @Override
    public void put(String key, V v) {
        System.out.println("Thread name " + Thread.currentThread().getName());
        concurrentMap.putIfAbsent(key, v);
    }

    @Override
    public void put(String key, V value, long timeout, TimeUnit timeUnit) {

    }

    @Override
    public V get(String key) {
        return (V) concurrentMap.get(key);
    }

    @Override
    public Map<String, V> allValue(String keyPrefix) {
        return Maps.newHashMap(concurrentMap);
    }

    @Override
    public void removeByKey(String key) {
        concurrentMap.remove(key);
    }
}
