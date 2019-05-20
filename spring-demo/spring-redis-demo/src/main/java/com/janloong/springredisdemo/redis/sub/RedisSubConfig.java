/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: RedisSubConfig.java
 : Author: janloongdoo@gmail.com
 : Date: 19-5-20 下午3:35
 : LastModify: 19-5-20 下午3:33
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springredisdemo.redis.sub;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-05-20 15:23
 */
@Configuration
public class RedisSubConfig {

    private static final String CHANNEL_DOO = "doo";
    private static final String CHANNEL_LOONG = "loong";

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter,
                                            MessageListenerAdapter listenerAdapter2) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //订阅了一个叫testchannel 的通道
        container.addMessageListener(listenerAdapter, new PatternTopic(RedisSubConfig.CHANNEL_DOO));
        //订阅了一个叫chat的频道
        container.addMessageListener(listenerAdapter2, new PatternTopic(RedisSubConfig.CHANNEL_LOONG));
        return container;
    }

    /**
     * 消息监听器适配器，绑定消息处理器，利用反射技术调用消息处理器的业务方法
     */
    @Bean
    MessageListenerAdapter listenerAdapter(MessageReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    MessageListenerAdapter listenerAdapter2(MessageReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage2");
    }

    /**
     * redis 读取内容的template
     */
    //@Bean
    //StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
    //    return new StringRedisTemplate(connectionFactory);
    //}

}
