package test.demo.component;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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

    public Boolean delate(String key) {
        return stringValueRedisTemplate.delete(key);
    }

    public void setIfAbsent(String key) {
        stringValueRedisTemplate.opsForValue().setIfAbsent(key, "1", 60, TimeUnit.SECONDS);
    }

    public Long sadd(String key, String value) {
        return stringValueRedisTemplate.opsForSet().add(key, value);
    }
}
