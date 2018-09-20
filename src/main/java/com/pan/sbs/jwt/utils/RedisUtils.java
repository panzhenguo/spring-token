package com.pan.sbs.jwt.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @PckageName com.pan.sbs.jwt.utils
 * @ClassName RedisUtils
 * @Description TODO
 * @Author pzg
 * @Date 2018/9/18
 * @Version 1.0.0
 * @Remark
 **/
@Component
public class RedisUtils {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @MethodName
     * @Description TODO
     * @Author pzg
     * @Date 2018/9/18
     * @Param
     * @Return
     * @Version 1.0.0
     **/
    public void set(String key, String value, long seconds) {
        stringRedisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    /**
     * @MethodName
     * @Description TODO
     * @Author pzg
     * @Date 2018/9/18
     * @Param
     * @Return
     * @Version 1.0.0
     **/
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void setExpire(String key, long expire) {
        stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * @MethodName
     * @Description TODO
     * @Author pzg
     * @Date 2018/9/18
     * @Param
     * @Return
     * @Version 1.0.0
     **/
    public long getExpire(String key) {
        return stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

}