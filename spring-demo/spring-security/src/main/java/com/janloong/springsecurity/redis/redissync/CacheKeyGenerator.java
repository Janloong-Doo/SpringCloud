/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: CacheKeyGenerator.java
 : Author: janloongdoo@gmail.com
 : Date: 2019/7/30 下午4:26
 : LastModify: 2019/7/30 下午4:26
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springsecurity.redis.redissync;

import org.aspectj.lang.ProceedingJoinPoint;


/**
 * 重复提交 加锁key的生成接口
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2020/4/8 10:55
 **/
public interface CacheKeyGenerator {
    /**
     * 获取AOP参数,生成指定缓存Key
     *
     * @param pjp PJP
     * @return 缓存KEY
     */
    String getLockKey(ProceedingJoinPoint pjp);
}
