/*
 * Copyright (c) 2020  All Rights Reserved.
 * ProjectName: cloud
 * FileName: HomeRouter.java
 * Author: janloongdoo@gmail.com
 * Website: www.janloong.com
 * Date: 2020/3/25 上午9:54
 * LastModify: 2020/3/25 上午9:54
 */

package com.janloong.demo.webflux.controller.router;

import com.janloong.demo.webflux.controller.handler.HomeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @version V1.0
 * @date 2020-03-25 09:54
 **/
//@Configuration
public class HomeRouter {

    @Bean
    public RouterFunction<ServerResponse> routeCity(HomeHandler homeHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/hello")
                                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                        homeHandler::helloHome);
    }
}
