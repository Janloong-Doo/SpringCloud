package com.janloong.common.autoconfigure;


import com.janloong.common.entity.JsonResult;
import com.janloong.common.entity.provider.JsonResultProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-05-21 00:18
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass(JsonResultProvider.class)
@EnableConfigurationProperties(JsonResult.class)
@Slf4j
public class JsonResultAutoConfigure {

    @Autowired
    private JsonResult jsonResult;

    @Bean
    @ConditionalOnMissingBean(JsonResultProvider.class)
    public JsonResultProvider getJsonResultProvider() {
        log.debug("JsonResult对象为:" + jsonResult.toString());
        return new JsonResultProvider(jsonResult);
    }
}
