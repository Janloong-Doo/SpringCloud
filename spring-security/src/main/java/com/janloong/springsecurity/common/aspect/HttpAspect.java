/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: HttpAspect.java
 : Author: janloongdoo@gmail.com
 : Date: 19-3-29 下午5:41
 : LastModify: 18-12-5 下午5:56
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springsecurity.common.aspect;

import com.janloong.springsecurity.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.Objects;

/**
 * http请求AOP
 * <p>
 * 对请求进行记录
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2017-12-28 16:20
 **/
@Aspect
@Component
@Slf4j
public class HttpAspect {

    @Value("${doo.jsonAble}")
    private Boolean jsonAble;
    @Value("${doo.jsonFile}")
    private String jsonFile;

    @AfterReturning(
            returning = "object",
            pointcut = "log()"
    )
    public void afterReturn(JoinPoint joinPoint, Object object) {
        Signature signature = joinPoint.getSignature();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();


        Object[] args = joinPoint.getArgs();
        log.error("\n请求结果 - " + className + "." + methodName + ": " + "\n"
                + "object:" + object + "\n"
        );
        log.info("jsonAble: " + jsonAble);
        if (jsonAble) {
            String name = methodName;
            String s = Arrays.toString(args);
            String replace = s.substring(1, s.length() - 1).replace(",", "-");
            if (replace.length() > 0 && !Objects.equals(replace, "null")) {
                name = name + "-" + replace;
            }
            String fileName = jsonFile + File.separator + name;
            log.info("\n - : " + "\n"
                    + "fileName:" + fileName + "\n"
            );
            JsonUtil.writeToFIle(fileName, object);
        }

    }

    @Pointcut("execution( * com.rzt.zdhprovider.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void logBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String remoteUser = request.getRemoteUser();
        int remotePort = request.getRemotePort();
        String remoteAddr = request.getRemoteAddr();
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        log.error("\n请求前 - : " + "\n"
                + "remoteUser:" + remoteUser + "\n"
                + "remotePort:" + remotePort + "\n"
                + "remoteAddr:" + remoteAddr + "\n"
                + "className:" + className + "\n"
                + "methodName:" + methodName + "\n"
                + "Params:" + Arrays.toString(args) + "\n");
    }
}
