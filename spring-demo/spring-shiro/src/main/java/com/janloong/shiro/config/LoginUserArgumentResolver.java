/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: LoginUserArgumentResolver.java
 : Author: janloongdoo@gmail.com
 : Date: 19-6-13 下午3:46
 : LastModify: 19-6-6 下午4:54
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.shiro.config;

import com.janloong.shiro.annotation.LoginAuth;
import com.janloong.shiro.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 参数解析器
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019/6/13 15:46
 **/
@Slf4j
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        final Method method = methodParameter.getMethod();
        final Class<?> clazz = Objects.requireNonNull(methodParameter.getMethod()).getDeclaringClass();

        boolean isHasLoginAuthAnn = clazz.isAnnotationPresent(LoginAuth.class) || Objects.requireNonNull(method).isAnnotationPresent(LoginAuth.class);
        boolean isHasLoginUserParameter = methodParameter.getParameterType().isAssignableFrom(User.class);
        System.out.println("是否解析参数");
        return isHasLoginAuthAnn && isHasLoginUserParameter;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) {
        return SecurityUtils.getSubject().getPrincipal();
    }
}
