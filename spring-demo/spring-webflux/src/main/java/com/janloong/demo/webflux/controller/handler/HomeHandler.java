/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: HomeHandler.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/3/25 上午9:50
 * LastModify: 2020/3/25 上午9:50
 */

package com.janloong.demo.webflux.controller.handler;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @version V1.0
 * @date 2020-03-25 09:50
 **/
//@Component
public class HomeHandler {

    public Mono<ServerResponse> helloHome(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject("Hello, City!"));
    }
}
