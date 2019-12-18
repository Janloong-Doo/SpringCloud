/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: ResultEnum.java
 : Author: janloongdoo@gmail.com
 : Date: 19-5-20 上午11:37
 : LastModify: 18-12-5 下午5:56
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.common.enums;

/**
 * 处理结果类型管理
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018/5/15 10:39
 **/
public enum ResultEnum {

    //基础码
    PARAM_ERROR(10001, "参数错误"),
    EMPTY_ID(10002, "id为空"),
    EMPTY_DATA(10003, "无相关数据"),
    REQUEST_ERROR(10004, "请求不合法"),
    REPEAT_SUBMIT(10005, "重复提交")

    //系统类
    , SUCCESS(0, "成功")
    , ERROR(-1, "未知错误")
    , UPDATE_ERROR(10010, "更新失败")
    , BEAN_ERROR(10011, "对象转换错误")
    , VERSION_ERROR(10012, "版本错误")
    , EMPTY_FILE(10013, "文件不能为空")
    , REMOTE_ERROR(10014, "远程调用失败")
    , REMOTE_PROVIDER_ERROR(10015, "提供远程服务失败")
    , FILE_UNZIP_ERROR(10016, "文件解压失败")
    , FILE_WRITE_ERROR(10017, "文件写入失败")


    //用户中心
    , EXITED_TEL(30001, "手机号已经注册")
    , ACCOUNT_ERROR(30002, "账号或密码错误")
    , EMPTY_USER(30003, "用户不存在")
    , EMPTY_OPERATION(30004, "没有操作权限")
    , ADMIN_EXITED(30005, "管理员已经注册")
    , ACCOUNT_NOLOGIN(30006, "用户未登录或身份已过期")

    //应用
    , APP_TYPE_ERROR(40001, "应用类型错误");


    private Integer code;
    private String msg;

    ResultEnum(Integer code) {
        this.code = code;
    }

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
