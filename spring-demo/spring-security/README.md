# spring security 
> 独立集成security学习记录

## 启动

>端口: `9001`
### 默认关闭
>默认中间件关闭，开启需要在相应位置注释或放开注释

1. RabbitMq: 
com.janloong.springsecurity.rabbitmq.config.RabbitMqConfig#@EnableRabbit
com.janloong.springsecurity.rabbitmq.config.RabbitMqConfig#@Configuration
com.janloong.springsecurity.rabbitmq.controller.SendController#RabbitTemplate
org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration
org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration
2. 

## 问题
    前后端分离情况下,security集成后导致原有跨域配置不生效问题
    
[参考](https://blog.csdn.net/qq_35494808/article/details/82998135)

[mq参考](https://blog.csdn.net/qq_35387940/article/details/100514134)