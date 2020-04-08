/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: RedisLockHelper.java
 : Author: janloongdoo@gmail.com
 : Date: 2019/7/30 下午2:55
 : LastModify: 2019/7/30 下午2:55
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springsecurity.redis.redissync;


import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.util.StringUtils;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * redis锁的操作
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-07-30 14:55
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisLockHelper {
    private static final String DELIMITER = "|";

    /**
     * 任务调度执行器初始化
     *
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/4/8 13:38
     **/
    //private static final ScheduledExecutorService EXECUTOR_SERVICE = Executors.newScheduledThreadPool(10);
    private static final ScheduledExecutorService EXECUTOR_SERVICE = new ScheduledThreadPoolExecutor(5);


    private final RedisTemplate<String, Object> stringRedisTemplate;
    //private final StringRedisTemplate stringRedisTemplate;

    public RedisLockHelper(RedisTemplate<String, Object> stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 获取锁（存在死锁风险）
     *
     * @param lockKey lockKey
     * @param value   value
     * @param time    超时时间
     * @param unit    过期单位
     * @return true or false
     */
    public boolean tryLock(final String lockKey, final String value, final long time, final TimeUnit unit) {
        Boolean execute = Boolean.FALSE;
        execute = stringRedisTemplate.execute((RedisCallback<Boolean>) connection -> connection.set(lockKey.getBytes(), value.getBytes(), Expiration.from(time, unit), RedisStringCommands.SetOption.SET_IF_ABSENT));
        return execute;
    }

    /**
     * 对key进行上锁 获取锁
     *
     * @param lockKey 加锁的缓存key
     * @param uuid    加锁的value
     * @param timeout 锁的过期时间,超时时间
     * @param unit    时间单位
     * @return boolean 上锁是否成功
     */
    public boolean lock(String lockKey, final String uuid, long timeout, final TimeUnit unit) {
        //过期时间转换
        final long milliseconds = Expiration.from(timeout, unit).getExpirationTimeInMilliseconds();
        //操作key是否成功，即key不存在时代表上锁成功.其中value过期的具体时间点和uuid
        Boolean success = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, (System.currentTimeMillis() + milliseconds) + DELIMITER + uuid);
        if (success != null && success) {
            //上锁成功后要对key做过期处理，防止系统崩溃后造成锁无法释放
            stringRedisTemplate.expire(lockKey, timeout, TimeUnit.SECONDS);
        } else {
            //当key存在时获取对应的value,判断是否过期,若过期则设置新的值,若时间不过期则认为上锁失败
            //修改值失败后获取旧的上锁key对应的value，并设置新的key对应的value为当前时间基础上对应的时间戳和新的uuid
            //String oldVal = String.valueOf(stringRedisTemplate.opsForValue().getAndSet(lockKey, (System.currentTimeMillis() + milliseconds) + DELIMITER + uuid));
            String oldVal = String.valueOf(stringRedisTemplate.opsForValue().get(lockKey));
            final String[] oldValues = oldVal.split(Pattern.quote(DELIMITER));
            //判断旧值的应过期时间是否已过期，若过期则应该代表上锁成功，否则即为上锁失败。
            if (Long.parseLong(oldValues[0]) + 1 <= System.currentTimeMillis()) {
                return true;
            } else {
                stringRedisTemplate.opsForValue().set(lockKey, (System.currentTimeMillis() + milliseconds) + DELIMITER + uuid);
            }
        }
        return success;
    }

    public boolean lock2(String lockKey, final String uuid, long timeout, final TimeUnit unit) {
        //过期时间转换
        final long milliseconds = Expiration.from(timeout, unit).getExpirationTimeInMilliseconds();
        //设置缓存key,若key不存在时代表上锁成功并设置过期时间
        Boolean success = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, (System.currentTimeMillis() + milliseconds) + DELIMITER + uuid, timeout, unit);
        //若上锁不成功，则进一步判断
        if (success != null && !success) {
            //修改值失败后获取旧的上锁key对应的value，并设置新的key对应的value为当前时间基础上对应的时间戳和新的uuid
            String oldVal = String.valueOf(stringRedisTemplate.opsForValue().get(lockKey));
            final String[] oldValues = oldVal.split(Pattern.quote(DELIMITER));
            //判断旧值的应过期时间是否已过期，若过期则应该代表上锁成功，否则即为上锁失败。
            if (Long.parseLong(oldValues[0]) + 1 <= System.currentTimeMillis()) {
                return true;
            } else {
                stringRedisTemplate.opsForValue().set(lockKey, (System.currentTimeMillis() + milliseconds) + DELIMITER + uuid);
            }
        }
        return success;
    }

    public void unlock(String lockKey, String value) {
        unlock(lockKey, value, 0, TimeUnit.MILLISECONDS);
    }

    /**
     * 延迟unlock
     *
     * @param lockKey   key
     * @param uuid      client(最好是唯一键的)
     * @param delayTime 延迟时间
     * @param unit      时间单位
     */
    public void unlock(final String lockKey, final String uuid, long delayTime, TimeUnit unit) {
        if (StringUtils.isEmpty(lockKey)) {
            return;
        }
        if (delayTime <= 0) {
            doUnlock(lockKey, uuid);
        } else {
            EXECUTOR_SERVICE.schedule(() -> doUnlock(lockKey, uuid), delayTime, unit);
        }
    }

    /**
     * @param lockKey key
     * @param uuid    client(最好是唯一键的)
     */
    private void doUnlock(final String lockKey, final String uuid) {
        String val = String.valueOf(stringRedisTemplate.opsForValue().get(lockKey));
        final String[] values = val.split(Pattern.quote(DELIMITER));
        if (values.length <= 0) {
            return;
        }
        if (uuid.equals(values[1])) {
            stringRedisTemplate.delete(lockKey);
        }
    }
}
