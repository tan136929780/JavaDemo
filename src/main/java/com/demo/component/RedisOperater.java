package com.demo.component;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Component
public class RedisOperater {
    @Resource
    StringRedisTemplate stringValueRedisTemplate;

    @Resource
    RedisTemplate<String, Object> stringObjectTemplate;

    public String get(String key) {
        return stringValueRedisTemplate.opsForValue().get(key);
    }

    public void set(String key, String value) {
        stringValueRedisTemplate.opsForValue().set(key, value);
    }
    public void setWithExpire(String key, String value, long time) {
        stringValueRedisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    public Boolean delate(String key) {
        return stringValueRedisTemplate.delete(key);
    }

    public void setIfAbsent(String key) {
        stringValueRedisTemplate.opsForValue().setIfAbsent(key, "1", 60, TimeUnit.SECONDS);
    }

    public Boolean expire(String key, Long time) {
        return stringValueRedisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    public Long sadd(String key, String value) {
        return stringValueRedisTemplate.opsForSet().add(key, value);
    }

    public Set<String> members(String key) {
        return stringValueRedisTemplate.opsForSet().members(key);
    }

    public Boolean isMember(String key, Object value) {
        return stringValueRedisTemplate.opsForSet().isMember(key, value);
    }

    public Long remove(String key, Object value) {
        return stringValueRedisTemplate.opsForSet().remove(key, value);
    }

    public Long lpush(String key, String value) {
        return stringValueRedisTemplate.opsForList().leftPush(key, value);
    }

    public Long rpush(String key, String value) {
        return stringValueRedisTemplate.opsForList().rightPush(key, value);
    }

    public String lpop(String key) {
        return stringValueRedisTemplate.opsForList().leftPop(key);
    }

    public String rpop(String key) {
        return stringValueRedisTemplate.opsForList().rightPop(key);
    }

    public List<String> range(String key, Long start, Long end) {
        return stringValueRedisTemplate.opsForList().range(key, start, end);
    }

    public void listSet(String key, Long index, String value) {
        stringValueRedisTemplate.opsForList().set(key, index, value);
    }

    public void hset(String key, Object hashKey, Object value) {
        stringValueRedisTemplate.opsForHash().put(key, hashKey, value);
    }

    public Object hget(String key, Object hashKey) {
        return stringValueRedisTemplate.opsForHash().get(key, hashKey);
    }

    public void hsetAll(String key, Map<?, ?> value) {
        stringValueRedisTemplate.opsForHash().putAll(key, value);
    }

    public Long hdel(String key, Object hashKey) {
        return stringValueRedisTemplate.opsForHash().delete(key, hashKey);
    }

    public Boolean hhaskey(String key, Object hashKey) {
        return stringValueRedisTemplate.opsForHash().hasKey(key, hashKey);
    }

    public Set<Object> hkeys(String key) {
        return stringValueRedisTemplate.opsForHash().keys(key);
    }

    public List<Object> hvalues(String key) {
        return stringValueRedisTemplate.opsForHash().values(key);
    }

    public Boolean hvalues(String key, Object hashKey, Object value) {
        return stringValueRedisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }
}
