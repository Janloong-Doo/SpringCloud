package com.janloong.shiro.config;

import com.janloong.shiro.annotation.LoginAuth;
import com.janloong.shiro.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class LoginAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();

            System.out.println("是否预处理方法");
            if (clazz.isAnnotationPresent(LoginAuth.class) || method.isAnnotationPresent(LoginAuth.class)) {
                User user = (User) SecurityUtils.getSubject().getPrincipal();
                try {
                    response.sendRedirect("/loginUrl");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return !ObjectUtils.isEmpty(user);
            }
            System.out.println("处理");
        }
        return true;
    }
}
