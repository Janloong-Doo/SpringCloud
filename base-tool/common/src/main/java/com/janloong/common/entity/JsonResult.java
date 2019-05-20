package com.janloong.common.entity;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-05-21 00:12
 */

@Data
@ConfigurationProperties(prefix = "doo.json")
public class JsonResult {

    public static final String JSONFILEPATH = "D://";
    public static final Boolean JSONABLE = false;

    private String jsonFilePath = JSONFILEPATH;
    private boolean jsonAble = JSONABLE;

}
