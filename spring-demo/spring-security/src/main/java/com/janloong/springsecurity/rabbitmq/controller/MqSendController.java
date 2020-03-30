///*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
// : Copyright (c) 2018  All Rights Reserved.
// : ProjectName: SpringCloud
// : FileName: MqSendController.java
// : Author: janloongdoo@gmail.com
// : Date: 18-11-1 下午5:57
// : LastModify: 18-11-1 下午5:57
// :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
//
//package com.janloong.springsecurity.rabbitmq.controller;
//
//
//import com.janloong.security.user.mq.MsgSender;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
// * @date 2018-11-01 17:57
// */
//@Slf4j
//@RestController
//@RequestMapping("mq")
//public class MqSendController {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//    //@Autowired
//    //private KafkaTemplate kafkaTemplate;
//
//    /**
//     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
//     * @date 2018/11/1 17:58
//     **/
//    @RequestMapping("/send")
//    public String send(String msg, String routeKey) {
//        log.info("入参: " + msg);
//        rabbitTemplate.convertAndSend(MsgSender.TOPIC_EXCHANGE_NAME, routeKey, msg);
//        //receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
//        return "success";
//    }
//
//    /**
//     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
//     * @date 2018/11/2 10:02
//     **/
//    @RequestMapping("/fanout")
//    public String fanout(String msg) {
//        log.info("入参: " + msg);
//        rabbitTemplate.convertAndSend(MsgSender.FANOUT_EXCHANGE_NAME, null, msg);
//
//        return "success";
//    }
//}
