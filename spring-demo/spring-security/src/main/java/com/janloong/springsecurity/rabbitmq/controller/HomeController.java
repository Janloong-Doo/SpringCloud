//package com.janloong.springsecurity.rabbitmq.controller;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
// * @date 2018-09-25 15:42
// */
//@RestController
////@RefreshScope
//@Slf4j
//public class HomeController {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @Value("${janloong.eureka.port}")
//    private String eurekaPort;
//
//    @Value("${janloong.eureka.ip}")
//    private String eurekaUrl;
//
//
//    /**
//     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
//     * @date 2018/9/25 15:42
//     **/
//    @RequestMapping("/home")
//    public String home() {
//        String s = "请求成功: " + ":" + eurekaPort;
//        log.info(s);
//        return s;
//    }
//
//    @RequestMapping("/home2")
//    public String home2() {
//        return "请求成功: " + eurekaUrl;
//    }
//
//    @RequestMapping("/provide")
//    public String provide() {
//        String janloong = consumeFeign.home("janloong");
//        log.info("result: " + janloong);
//        return janloong;
//    }
//}
