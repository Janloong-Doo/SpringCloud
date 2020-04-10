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

import com.janloong.springsecurity.rabbitmq.config.TopicQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @version V1.0
 * @date 2020-04-02 14:11
 **/
@Service
@Slf4j
public class ConfirmSend implements MsgSendHandler {


    @Override
    public String getSendType() {
        return "confirm";
    }

    @Override
    public Object sendMsg(RabbitTemplate rabbitTemplate) {
        try {

            //String sendRoutingKey5 = "doo.test.queue3";
            //System.out.println("send start - routingKey" + sendRoutingKey5);
            //Map<String, String> map5 = Map.of("routingKey", sendRoutingKey5, "content", "hello,JanloongDoo. I'm from ");
            ////rabbitTemplate.convertAndSend(TopicQueueConfig.TOPIC_EXCHANGE, sendRoutingKey5, map5, message -> {
            ////    message.getMessageProperties().setReplyTo();
            ////});
            //Thread.sleep(2000);
            //System.out.println("send over ");

            String sendRoutingKey6 = TopicQueueConfig.TOPIC_ROUTING_KEY4;
            System.out.println("send start- routingKey" + sendRoutingKey6);
            //for (int i = 0; i < 20; i++) {
            CorrelationData correlationData = new CorrelationData("janloongdoo");
            Map<String, String> map6 = Map.of("routingKey", sendRoutingKey6, "content", "hello,JanloongDoo. I'm from ");
            rabbitTemplate.convertAndSend(TopicQueueConfig.TOPIC_EXCHANGE, sendRoutingKey6, map6, correlationData);
            CorrelationData.Confirm confirm = correlationData.getFuture().get(10, TimeUnit.SECONDS);
            //先于全局confirm确认响应
            System.out.println("Confirm  exchange received, ack = " + confirm.isAck());
            System.out.println("Confirm  exchange received, ack = " + confirm.isAck());
            //}
            System.out.println("send over");
            return null;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }
}
