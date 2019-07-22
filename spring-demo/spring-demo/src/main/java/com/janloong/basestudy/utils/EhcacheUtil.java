/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: EhcacheUtil.java
 : Author: janloongdoo@gmail.com
 : Date: 19-7-22 上午10:58
 : LastModify: 19-7-22 上午10:58
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.basestudy.utils;


import lombok.extern.slf4j.Slf4j;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * ehcache 无xml配置
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-07-22 10:58
 */
@EnableCaching
@Configuration
@Slf4j
public class EhcacheUtil {


    @Bean
    public CacheConfiguration getEhcacheConfig() {
        return CacheConfigurationBuilder.newCacheConfigurationBuilder(
                String.class
                , String.class
                , ResourcePoolsBuilder
                        .newResourcePoolsBuilder()
                        //设置缓存堆容纳元素个数(JVM内存空间)超出个数后会存到offheap中
                        .heap(1000L, EntryUnit.ENTRIES)
                        //设置堆外储存大小(内存存储) 超出offheap的大小会淘汰规则被淘汰
                        .offheap(100L, MemoryUnit.MB)
                        // 配置磁盘持久化储存(硬盘存储)用来持久化到磁盘,这里设置为false不启用
                        //.disk(500L, MemoryUnit.MB, false)
                        .build()
        )
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(1)))
                .build();
    }

    @Bean
    public CacheManager getCacheManager() {
        return CacheManagerBuilder.newCacheManagerBuilder()
                .withCache("defaultCache", this::getEhcacheConfig)
                // 硬盘持久化地址
                //.with(CacheManagerBuilder.persistence("D:/ehcacheData"))
                .build(true);

    }
}
