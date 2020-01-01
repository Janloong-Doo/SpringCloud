
package com.janloong.consumer.feign.handler;


import com.janloong.common.enums.ResultEnum;
import com.janloong.common.utils.ResponseResult;
import com.janloong.consumer.feign.ProviderFeign;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019/12/31 20:13
 **/
@Component
public class ProviderHandler implements ProviderFeign {
    @Override
    public ResponseResult hello(@RequestParam("name") String name) {
        return ResponseResult.error(ResultEnum.REMOTE_ERROR.getCode(), ResultEnum.REMOTE_ERROR.getMsg());
    }

}
