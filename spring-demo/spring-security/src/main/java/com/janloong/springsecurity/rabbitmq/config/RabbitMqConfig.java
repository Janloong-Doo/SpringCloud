package com.janloong.springsecurity.rabbitmq.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ErrorHandler;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2020-04-03 00:16
 */
@EnableRabbit
//@Configuration
@Slf4j
public class RabbitMqConfig {

    @Autowired
    private MqProducerErrorHandler errorHandler;

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory,
                                                                               SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setErrorHandler(errorHandler());
        return factory;
    }
    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //每个rabbittemplate 仅支持一个回调,如果需要不同的回调处理可以声明多个template,或根据相关数据内容进行业务划分处理
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause));
        //消息发送不到队列时触发失败回调
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message));
        return rabbitTemplate;
    }

    @Bean
    public ErrorHandler errorHandler() {
        return new ConditionalRejectingErrorHandler(errorHandler);
    }
}
