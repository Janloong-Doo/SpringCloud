package com.janloong.springsecurity.config.validatecode;

import lombok.Data;

/**
 * 图形验证码  配置读取类
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019/12/18 16:39
 **/
@Data
public class ImageCodeProperties {
    /**
     * 验证码宽度
     */
    private int width = 67;
    /**
     * 高度
     */
    private int height = 23;
    /**
     * 长度（几个数字）
     */
    private int length = 4;
    /**
     * 过期时间
     */
    private int expireIn = 60;

    /**
     * 需要图形验证码的 url
     */
    private String url;
}
