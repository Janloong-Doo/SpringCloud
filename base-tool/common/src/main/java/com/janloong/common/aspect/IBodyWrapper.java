package com.janloong.common.aspect;

/**
 * {@link com.janloong.common.annotation.BodyWrapper}业务处理操作
 *
 * @author <a href ="https://blog.janloong.com">Janloong Doo</a>
 * @version 1.0.0
 * @since 2020-04-26 17:57
 **/
public interface IBodyWrapper {
    
    /**
     * 操作方法
     *
     * @return 返回处理后的值
     */
    Object process();
}
