#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author XanderYe
 * @description:
 * @date 2021/7/7 8:30
 */
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 存储字符串
     * @param key
     * @param value
     * @return void
     * @author XanderYe
     * @date 2021/7/27
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 存储字符串带过期时间
     * @param key
     * @param value
     * @param timeout
     * @param timeUnit
     * @return void
     * @author XanderYe
     * @date 2021/7/27
     */
    public void set(String key, Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 获取字符串
     * @param key
     * @return java.lang.Object
     * @author XanderYe
     * @date 2021/7/27
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 存储列表
     * @param key
     * @param value
     * @return void
     * @author XanderYe
     * @date 2021/7/27
     */
    public void sAdd(String key, Object... value) {
        redisTemplate.opsForSet().add(key, value);
    }

    /**
     * 获取列表
     * @param key
     * @return void
     * @author XanderYe
     * @date 2021/7/27
     */
    public Set<Object> sMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 存储哈希表
     * @param h
     * @param hKey
     * @param value
     * @return void
     * @author XanderYe
     * @date 2021/7/27
     */
    public void hSet(String h, String hKey, Object value) {
        redisTemplate.opsForHash().put(h, hKey, value);
    }

    /**
     * 获取哈希表
     * @param h
     * @param hKey
     * @return void
     * @author XanderYe
     * @date 2021/7/27
     */
    public Object hGet(String h, String hKey) {
        return redisTemplate.opsForHash().get(h, hKey);
    }

    /**
     * 删除
     * @param key
     * @return void
     * @author XanderYe
     * @date 2021/7/27
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除
     * @param keys
     * @return void
     * @author XanderYe
     * @date 2021/7/27
     */
    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 是否有键
     * @param key
     * @return java.lang.Boolean
     * @author XanderYe
     * @date 2021/7/27
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 设置过期时间
     * @param key
     * @param timeout
     * @param unit
     * @return java.lang.Boolean
     * @author XanderYe
     * @date 2021/7/27
     */
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }
}
