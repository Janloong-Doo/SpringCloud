package com.janloong.demo.controller;


import com.janloong.common.utils.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2020-03-24 21:57
 */
@RestController
//@RequestMapping("/home")
public class HomeController {

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/3/24 0024 21:57
     **/
    @GetMapping("/hello")
    //public Mono<ResponseResult> hello() {
    public Mono<String> hello() {
        return Mono.just("hello, webflux");
    }
}
