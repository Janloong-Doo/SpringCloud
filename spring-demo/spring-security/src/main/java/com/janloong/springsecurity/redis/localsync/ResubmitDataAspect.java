/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: ResubmitDataAspect.java
 : Author: janloongdoo@gmail.com
 : Date: 2019/7/30 上午11:36
 : LastModify: 2019/7/30 上午11:36
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springsecurity.redis.localsync;


import com.janloong.common.enums.ResultEnum;
import com.janloong.common.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 重复提交业务处理
 * <p>
 * 本处是依据请求参数进行相应的重复请求限制。
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-07-30 11:36
 */
@Aspect
@Component
@Slf4j
public class ResubmitDataAspect {

    private final static String DATA = "data";
    private final static Object PRESENT = new Object();

    @Around("@annotation(com.janloong.springsecurity.redis.localsync.Resubmit)")
    public Object handleResubmit(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //获取注解信息
        Resubmit annotation = method.getAnnotation(Resubmit.class);
        int delaySeconds = annotation.delaySeconds();
        Object[] pointArgs = joinPoint.getArgs();
        String key = "";

        List<Object> objects = Arrays.asList(pointArgs);
        String collect = objects.stream().map(String::valueOf).peek(System.out::print).collect(Collectors.joining());
        key = ResubmitLock.handleKey(collect);
        //获取第一个参数
        //Object firstParam = pointArgs[0];
        //if (firstParam instanceof RequestDTO) {
        //    //解析参数
        //    JSONObject requestDTO = JSONObject.parseObject(firstParam.toString());
        //    JSONObject data = JSONObject.parseObject(requestDTO.getString(DATA));
        //    if (data != null) {
        //        StringBuffer sb = new StringBuffer();
        //        data.forEach((k, v) -> {
        //            sb.append(v);
        //        });
        //        //生成加密参数 使用了content_MD5的加密方式
        //        key = ResubmitLock.handleKey(sb.toString());
        //    }
        //}
        //执行锁
        boolean lock = false;
        try {
            //对key进行上锁，上锁成功进行下一步业务，失败则进入重复提交业务异常
            lock = ResubmitLock.getInstance().lock(key, PRESENT);
            if (lock) {
                //放行
                return joinPoint.proceed();
            } else {
                //响应重复提交异常
                return ResponseResult.error(ResultEnum.REPEAT_SUBMIT.getCode(), ResultEnum.REPEAT_SUBMIT.getMsg());
            }
        } finally {
            //加锁成功后需要在一定时间后解锁该key，设置解锁key和解锁时间
            ResubmitLock.getInstance().unLock(lock, key, delaySeconds);
        }
    }

}
