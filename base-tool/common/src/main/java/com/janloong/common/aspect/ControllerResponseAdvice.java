package com.janloong.common.aspect;

import com.janloong.common.annotation.BodyWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * controller层响应结果切面
 *
 * @author <a href ="https://blog.janloong.com">Janloong Doo</a>
 * @version 1.0.0
 * @since 2020-04-26 17:10
 **/
@Configuration
@ControllerAdvice
public class ControllerResponseAdvice implements ResponseBodyAdvice {
    @Resource
    private IBodyWrapper iBodyWrapper;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        //RestController restController = (RestController) returnType.getDeclaringClass().getAnnotation(RestController.class);
        ResponseBody responseBody = (ResponseBody) returnType.getMethodAnnotation(ResponseBody.class);
        boolean wrapper = false;
        BodyWrapper bodyWrapper = (BodyWrapper) returnType.getMethodAnnotation(BodyWrapper.class);
        if (Objects.nonNull(bodyWrapper)) {
            wrapper = bodyWrapper.value();
        }
        return Objects.nonNull(responseBody) && wrapper;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        //添加注解后实际业务操作
        return iBodyWrapper.process();
    }
}
