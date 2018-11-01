package com.janloong.security.user.controller;


import com.janloong.security.user.feign.ConsumeFeign;
import com.janloong.security.user.mq.MsgSender;
import com.janloong.security.user.mq.Receiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-09-25 15:42
 */
@RestController
@RefreshScope
@Slf4j
public class HomeController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${janloong.eureka.port}")
    private String eurekaPort;

    @Value("${janloong.eureka.ip}")
    private String eurekaUrl;

    @Autowired
    private ConsumeFeign consumeFeign;

    @Autowired
    private Receiver receiver;

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018/9/25 15:42
     **/
    @RequestMapping("/home")
    public String home() {
        String s = "请求成功: " + ":" + eurekaPort;
        log.info(s);
        return s;
    }

    @RequestMapping("/home2")
    public String home2() {
        return "请求成功: " + eurekaUrl;
    }

    @RequestMapping("/provide")
    public String provide() {
        String janloong = consumeFeign.home("janloong");
        log.info("result: " + janloong);
        return janloong;
    }

    @RequestMapping("/send")
    public String send(String msg) {
        rabbitTemplate.convertAndSend(MsgSender.TOPIC_EXCHANGE_NAME, "foo.bar.baz", msg);
        try {
            receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            log.error("同步等待错误");
            e.printStackTrace();
        }
        log.info("消息队列处理成功");
        return msg;
    }
}
