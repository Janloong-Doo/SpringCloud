package com.janloong.common.entity;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href ="https://blog.janloong.com">Janloong Doo</a>
 * @version 1.0.0
 * @since 2019-05-21 00:12
 */
@Data
@ConfigurationProperties(prefix = "doo.json")
public class JsonResult {

    public static final String JSONFILEPATH = "D://";
    public static final Boolean JSONABLE = false;

    private String filePath = JSONFILEPATH;
    private boolean able = JSONABLE;

}
