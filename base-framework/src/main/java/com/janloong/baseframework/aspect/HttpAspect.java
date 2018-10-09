package com.janloong.baseframework.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

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
public class HttpAspect {
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @AfterReturning(
            returning = "object",
            pointcut = "log()"
    )
    public void afterReturn(JoinPoint joinPoint, Object object) {
        Signature signature = joinPoint.getSignature();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        logger.error("\n请求结果 - " + className + "." + methodName + ": " + "\n"
                + "object:" + object + "\n"
        );

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
        logger.error("\n请求前 - : " + "\n"
                + "remoteUser:" + remoteUser + "\n"
                + "remotePort:" + remotePort + "\n"
                + "remoteAddr:" + remoteAddr + "\n"
                + "className:" + className + "\n"
                + "methodName:" + methodName + "\n"
                + "Params:" + Arrays.toString(args) + "\n");
    }
}
