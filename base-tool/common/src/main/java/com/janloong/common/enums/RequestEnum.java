/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: RequestEnum.java
 : Author: janloongdoo@gmail.com
 : Date: 19-5-17 下午5:34
 : LastModify: 18-10-16 上午9:28
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.common.enums;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018/8/1 10:20
 **/
public enum RequestEnum {
    a01("jdgjzt", "查询节点状态告警信息")
    , a02("yyztgj", "查询应用状态告警信息")
    , a03("cpkjgj", "磁盘空间告警")
    , a04("wkllgj", "查询网卡流量告警信息")
    , a05("qzllgj", "查询前置链路告警信息")
    , a06("cpu", "查询CPU告警信息")
    , a07("ncztgj", "查询内存状态告警信息")
    , a08("cpzlzt", "磁盘阵列状态监视")
    , a09("sjkzt", "数据库状态监视")
    , W01("test1", "测控")
    , W02("test2", "测控")

    ;


    RequestEnum(String key, String des) {
        this.key = key;
        this.des = des;
    }

    private String key;
    private String des;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
