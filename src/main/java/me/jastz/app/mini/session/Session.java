package me.jastz.app.mini.session;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zhiwen
 */
public interface Session<V> {

    void put(String key, V value);

    void put(String key, V value, long timeout, TimeUnit timeUnit);

    V get(String key);

    Map<String, V> allValue(String keyPrefix);

    void removeByKey(String key);

}
