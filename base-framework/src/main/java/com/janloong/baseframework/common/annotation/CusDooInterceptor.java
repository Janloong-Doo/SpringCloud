/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: CusDooInterceptor.java
 : Author: janloongdoo@gmail.com
 : Date: 18-11-5 上午11:13
 : LastModify: 18-11-5 上午11:13
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.baseframework.common.annotation;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-11-05 11:13
 */
@Aspect
@Component
@Slf4j
public class CusDooInterceptor implements Ordered {

    @Override
    public int getOrder() {
        return 0;
    }

    @Around("@annotation(CustomDoo)")
    public Object customProceed(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        CustomDoo annotation = methodSignature.getMethod().getAnnotation(CustomDoo.class);
        String doo = annotation.doo();
        String value = annotation.value();

        log.info("自定义注解处理 start" + value + "  " + doo);
        Object proceed = proceedingJoinPoint.proceed();
        return proceed;
    }

}
