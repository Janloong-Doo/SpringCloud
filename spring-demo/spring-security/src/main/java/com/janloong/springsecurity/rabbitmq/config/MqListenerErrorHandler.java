package com.janloong.springsecurity.rabbitmq.config;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.stereotype.Component;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2020-04-06 04:31
 */
@Component
public class MqListenerErrorHandler implements RabbitListenerErrorHandler {
    @Override
    public Object handleError(Message amqpMessage, org.springframework.messaging.Message<?> message, ListenerExecutionFailedException exception) throws Exception {
        System.out.println("listener custom errorHandler");
        return null;
    }
}
