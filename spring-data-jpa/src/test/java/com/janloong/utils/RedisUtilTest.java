package com.janloong.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisUtilTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test() {
        RedisSerializer<?> defaultSerializer = redisUtil.template.getDefaultSerializer();
        System.out.println(defaultSerializer);
        System.out.println(redisUtil.template.getKeySerializer());
        System.out.println(redisUtil.template.getValueSerializer());
        System.out.println(redisUtil.template.getStringSerializer());
        System.out.println(redisUtil.template.getHashKeySerializer());
        System.out.println(redisUtil.template.getHashValueSerializer());
        //redisUtil.set("doo", "janoong");
        //Object a = redisUtil.get("a");
        //System.out.println("===============");
        //System.out.println(a);
        //Assert.assertEquals("a", a);
        //redisUtil.addLink("l", "1");
        //redisUtil.addLink("l", "2");
        //redisUtil.addLink("l", "3");
    }

    @Test
    public void test2() {
        long l = redisUtil.getLinkSize2("f");
        System.out.println("========");
        System.out.println(l);
    }

    //@Autowired
    //RedisConnection redisConnection;ioe

    @Test
    public void redisSend() {

        //redisConnection.publish("hello".getBytes(), "janloongdoo".getBytes());
        redisUtil.template.convertAndSend("hello", "janloongdoo");


    }

}