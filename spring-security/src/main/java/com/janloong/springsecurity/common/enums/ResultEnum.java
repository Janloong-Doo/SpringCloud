/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: ResultEnum.java
 : Author: janloongdoo@gmail.com
 : Date: 19-3-29 下午5:41
 : LastModify: 18-12-5 下午5:56
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springsecurity.common.enums;

/**
 * 处理结果类型管理
 *
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018/5/15 10:39
 **/
public enum ResultEnum {

    //基础码
    参数错误(10001),
    id为空(10002),
    无相关数据(10003)

    //系统类
    ,成功(0)
    ,未知错误(-1)
    ,更新失败(10010)
    ,对象转换错误(10011)
    ,版本名称不合法(10012)
    ,文件不能为空(10013)
    ,远程调用失败(10014)
    ,提供远程服务失败(10015)
    ,文件解压失败(10016)
    ,文件写入失败(10017)


    //用户中心
    ,手机号已经注册(30001)
    ,账号或密码错误(30002)
    ,用户不存在(30003)
    ,没有操作权限(30004)
    ,管理员已经注册(30005)

    //应用
    ,应用类型错误(40001)
    ;


    private Integer code;

    ResultEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
