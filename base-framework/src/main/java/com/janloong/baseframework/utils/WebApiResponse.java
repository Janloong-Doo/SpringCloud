package com.janloong.baseframework.utils;


import java.io.Serializable;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-05-17 22:24
 */
public class WebApiResponse implements Serializable {
    private boolean success;
    private String error;
    private Object Data;

    public WebApiResponse() {
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getData() {
        return this.Data;
    }

    public void setData(Object data) {
        this.Data = data;
    }

    public static WebApiResponse success(Object data) {
        WebApiResponse response = new WebApiResponse();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static WebApiResponse erro(String errorMessage) {
        WebApiResponse response = new WebApiResponse();
        response.setSuccess(false);
        response.setData(errorMessage);
        response.setError(errorMessage);
        return response;
    }

    @Override
    public String toString() {
        return "WebApiResponse{" +
                "success=" + success +
                ", error='" + error + '\'' +
                ", Data=" + Data +
                '}';
    }
}
