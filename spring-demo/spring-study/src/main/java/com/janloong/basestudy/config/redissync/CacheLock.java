/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: CacheLock.java
 : Author: janloongdoo@gmail.com
 : Date: 2019/7/30 下午3:00
 : LastModify: 2019/7/30 下午3:00
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.basestudy.config.redissync;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019/7/30 15:01
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLock {

    String prefix() default "repeatLock";

    int expire() default 20;

    TimeUnit timeUnit() default TimeUnit.SECONDS;

    String delimiter() default ":";
}
