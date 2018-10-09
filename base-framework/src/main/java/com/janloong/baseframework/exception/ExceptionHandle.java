package com.janloong.baseframework.exception;

import com.janloong.baseframework.utils.WebApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 异常处理类
 * <p>
 * controller层异常外抛处理类
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018/5/15 10:39
 **/
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = {RuntimeException.class, Exception.class})
    @ResponseBody
    public WebApiResponse handle(Exception e) {
        if (e instanceof BusinessException) {
            BusinessException bussinesException = (BusinessException) e;
            if (bussinesException.isSuccess()) {
                logger.info("[业务正常] [{} - {}]", bussinesException.getCode(), bussinesException.getMsg());
                return WebApiResponse.success(bussinesException.getCode() + " - " + bussinesException.getMsg());
            } else {
                logger.error("[业务异常] [{} - {}]", bussinesException.getCode(), bussinesException.getMsg());
                return WebApiResponse.erro(bussinesException.getCode() + " - " + bussinesException.getMsg());
            }
        } else {
            logger.error("[系统异常]: {}", e);
            return WebApiResponse.erro("未知系统错误 : " + e);
        }
    }
}
