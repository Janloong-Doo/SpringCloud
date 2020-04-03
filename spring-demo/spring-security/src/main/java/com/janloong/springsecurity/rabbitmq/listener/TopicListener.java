package com.janloong.springsecurity.rabbitmq.listener;


import com.janloong.springsecurity.rabbitmq.config.MqErrorhandler;
import com.janloong.springsecurity.rabbitmq.config.TopicQueueConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

/**
 * 消费者监听设置
 * <p>
 * autoDelete 为true时，没有消费者队列就会自动删除，可以通过注解中的属性手动创建路由和队列。一般适用于临时队列
 * durable 队列、路由、绑定等信息是否会持久，默认指定一个队列名称时会持久
 * deliveryTag 相当于消息的唯一标识，用来mq辨别哪个消息ack/nck
 * channel mq和消费者之间的管道用来ack/nck
 * <p>
 * multiple 【待定】发送时是否携带对应的delivery信息
 * </p>
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2020-03-30 23:17
 */
@Component
public class TopicListener {

    @Autowired
    private MqErrorhandler mqErrorhandler;

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

    /**
     * 自动创建板顶
     * <p>
     * 队列不存在时会自动创建一个队列，并与exchange绑定
     * </P>
     *
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/4/3 11:32
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = TopicQueueConfig.TOPIC_QUEUE4, durable = "true", autoDelete = "true")
            , exchange = @Exchange(value = TopicQueueConfig.TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC)
            , key = TopicQueueConfig.TOPIC_ROUTING_KEY4)
            , ackMode = "MANUAL"
            //, ackMode = "AUTO"
            //,concurrency = "4"
            //, concurrency = "2-5"
            , errorHandler = MqErrorhandler.class
    )
    //public void listen5(Map<String, Object> content, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
    public void listen5(Message message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        if (true) {
            throw new ListenerExecutionFailedException("手动抛出监听异常", new Throwable("手动Throwable"), message);
        }
        //System.out.println(content.get("content")
        //        + "\nsendRoutingKey:" + content.get("routingKey")
        //        + "\n reciveQueue:" + TopicQueueConfig.TOPIC_QUEUE4
        //        + "\nwithKey:" + TopicQueueConfig.TOPIC_ROUTING_KEY4
        //        + "\ndeliveryTag:" + deliveryTag
        //        + "\n");
        boolean b = new Random().nextBoolean();
        System.out.println(b);
        if (b) {
            System.out.println("成功" + deliveryTag);
            //消费成功时，信道返回consumer ack信息
            channel.basicAck(deliveryTag, true);
        } else {
            System.out.println("失败：" + deliveryTag);
            // 消费失败时，第三个参数true，表示这个消息会重新进入队列
            channel.basicNack(deliveryTag, true, true);
        }
    }
}
