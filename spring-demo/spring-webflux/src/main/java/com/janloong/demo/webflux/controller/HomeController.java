package com.janloong.demo.webflux.controller;


import com.janloong.common.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2020-03-24 21:57
 */
@RestController
@RequestMapping("/home")
@Slf4j
public class HomeController {

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/3/24 0024 21:57
     **/
    @GetMapping("/hello")
    public Mono<ResponseResult> hello() {
        return Mono.just(ResponseResult.success("hello, webflux"));
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/3/25 10:08
     **/
    @RequestMapping("/async")
    //public Mono<ResponseResult> async() {
    public Mono<Object> async() {

        return Mono.create(monoSink -> {
            log.info("mono创建");
            monoSink.success(ResponseResult.success("hello, mono created"));
        })
                .doOnSubscribe(subscription -> { //当订阅者去订阅发布者的时候，该方法会调用
                    log.info("被订阅了?");
                    log.info("{}", subscription);
                }).doOnNext(o -> { //当订阅者收到数据时，改方法会调用
                    log.info("订阅者收到消息");
                    log.info("{}", o);
                });
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/3/25 10:19
     **/
    @RequestMapping("/flux")
    public Flux<String> flux() {
        return Flux.just("hello", "webflux", "spring", "boot");
    }

    /**
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2020/3/25 10:19
     **/
    @RequestMapping("/flux2")
    public Flux<ResponseResult> flux2() {
        return Flux.just(ResponseResult.success("hello"), ResponseResult.success("webflux"), ResponseResult.success("spring"), ResponseResult.success("boot"));
    }
}
