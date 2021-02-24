package com.janloong.common.annotation;

import java.lang.annotation.*;

/**
 * body体自动包装注解（可扩展）
 * <p>
 * 例如：
 * <pre>{@code
 * public String getString(){
 *   return "Hello,Janloong Doo";
 * }
 * 返回 Hello,Janloong Doo.
 * @BodyWrapper
 * public String getString(){
 *   return "Hello,Janloong Doo";
 * }
 * 加入BodyWrapper的操作为 "".toLowerCase()
 * 则返回 hello,janloong doo.
 * }</pre>
 *
 * @author <a href ="https://blog.janloong.com">Janloong Doo</a>
 * @version 1.0.0
 * @since 2020-04-26 17:21
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BodyWrapper {
    /**
     * @return 是否开启
     */
    boolean value() default true;

    /**
     * @return 是否加密
     */
    boolean encrypt() default false;
}
