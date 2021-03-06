/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: TopicSend.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/4/2 下午2:11
 * LastModify: 2020/4/2 下午2:11
 */

package com.janloong.springsecurity.rabbitmq.handler;

import com.janloong.springsecurity.rabbitmq.config.DirectQueueConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @version V1.0
 * @date 2020-04-02 14:11
 **/
@Service
public class DirectSend implements MsgSendHandler {

    @Override
    public String getSendType() {
        return "direct";
    }

    @Override
    public Object sendMsg(RabbitTemplate rabbitTemplate) {
        try {

            Thread.sleep(2000);
            String sendRoutingKey = DirectQueueConfig.TOPIC_ROUTING_KEY;
            System.out.println("send start- routingKey" + sendRoutingKey);
            Map<String, Object> map = Map.of("routingKey", sendRoutingKey, "content", "hello,JanloongDoo. I'm from ");
            rabbitTemplate.convertAndSend(DirectQueueConfig.TOPIC_EXCHANGE, sendRoutingKey, map);
            Thread.sleep(2000);
            System.out.println("send over");
            String sendRoutingKey2 = DirectQueueConfig.TOPIC_ROUTING_KEY2;
            System.out.println("send start1- routingKey" + sendRoutingKey2);
            Map<String, String> map2 = Map.of("routingKey", sendRoutingKey2, "content", "hello,JanloongDoo. I'm from ");
            rabbitTemplate.convertAndSend(DirectQueueConfig.TOPIC_EXCHANGE, sendRoutingKey2, map2);
            Thread.sleep(2000);
            System.out.println("send over1");

            String sendRoutingKey3 = DirectQueueConfig.TOPIC_ROUTING_KEY3;
            System.out.println("send start2- routingKey" + sendRoutingKey3);
            Map<String, String> map3 = Map.of("routingKey", sendRoutingKey3, "content", "hello,JanloongDoo. I'm from ");
            rabbitTemplate.convertAndSend(DirectQueueConfig.TOPIC_EXCHANGE, sendRoutingKey3, map3);
            Thread.sleep(2000);
            System.out.println("send over2");

            String sendRoutingKey4 = "*.direct.*";
            System.out.println("send start3- routingKey" + sendRoutingKey4);
            Map<String, String> map4 = Map.of("routingKey", sendRoutingKey4, "content", "hello,JanloongDoo. I'm from ");
            rabbitTemplate.convertAndSend(DirectQueueConfig.TOPIC_EXCHANGE, sendRoutingKey4, map4);
            Thread.sleep(2000);
            System.out.println("send over3");

            String sendRoutingKey5 = "doo.direct.test";
            System.out.println("send start4- routingKey" + sendRoutingKey5);
            Map<String, String> map5 = Map.of("routingKey", sendRoutingKey5, "content", "hello,JanloongDoo. I'm from ");
            rabbitTemplate.convertAndSend(DirectQueueConfig.TOPIC_EXCHANGE, null, map5);
            System.out.println("send over4");
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
