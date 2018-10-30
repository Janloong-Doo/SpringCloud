/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: RibbonController.java
 : Author: janloongdoo@gmail.com
 : Date: 18-10-30 上午10:41
 : LastModify: 18-10-12 上午10:50
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.ribbon.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-04-08 15:42
 */
@RestController
public class RibbonController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(name = "ribbon")
    @HystrixCommand(fallbackMethod = "fallBack")
    public String ribbon() {
        String forObject = restTemplate.getForObject("http://provide-1/home", String.class);
        return "ribbon方式获得: " + forObject;
    }

    public String fallBack() {
        return "服务器出错";
    }


    //@Autowired
    private LoadBalancerClient client;

    //@RequestMapping(value = "/lb", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceInstance lb() {
        ServiceInstance si = client.choose("spring-lb-provider");
        return si;
    }

    //@Autowired
    private SpringClientFactory factory;

    /**
     * 覆盖默认配置
     * default:
     * ribbon:
     * NFLoadBalancerRuleClassName: org.crazyit.cloud.MyRule
     *
     * @return
     */
    //@RequestMapping(value = "/fa", method = RequestMethod.GET)
    public String factory() {
        ZoneAwareLoadBalancer lb = (ZoneAwareLoadBalancer) factory.getLoadBalancer("default");
        System.out.println(lb.getRule().getClass().getName());

        ZoneAwareLoadBalancer lb2 = (ZoneAwareLoadBalancer) factory.getLoadBalancer("spring-lb-provider");
        System.out.println(lb2.getRule().getClass().getName());
        return "";
    }
}
