package com.janloong.springsecurity.rabbitmq.listener;


import com.janloong.springsecurity.rabbitmq.config.TopicQueueConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2020-03-30 23:17
 */
@Component
public class TopicListener {

    @RabbitListener(queues = TopicQueueConfig.TOPIC_QUEUE1)
    public void listen2(Map<String, Object> content) {
        System.out.println(content.get("content")
                + "\nsendRoutingKey:" + content.get("routingKey")
                + "\n reciveQueue:" + TopicQueueConfig.TOPIC_QUEUE1
                + "\nwithKey:" + TopicQueueConfig.TOPIC_ROUTING_KEY
                + "\n");
    }

    @RabbitListener(queues = TopicQueueConfig.TOPIC_QUEUE2)
    public void listen3(Map<String, Object> content) {
        System.out.println(content.get("content")
                + "\nsendRoutingKey:" + content.get("routingKey")
                + "\n reciveQueue:" + TopicQueueConfig.TOPIC_QUEUE2
                + "\nwithKey:" + TopicQueueConfig.TOPIC_ROUTING_KEY2
                + "\n");
    }

    @RabbitListener(queues = TopicQueueConfig.TOPIC_QUEUE3)
    public void listen4(Map<String, Object> content) {
        System.out.println(content.get("content")
                + "\nsendRoutingKey:" + content.get("routingKey")
                + "\n reciveQueue:" + TopicQueueConfig.TOPIC_QUEUE3
                + "\nwithKey:" + TopicQueueConfig.TOPIC_ROUTING_KEY3
                + "\n");
    }
}
