/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: AuthorInfoProvider.java
 : Author: janloongdoo@gmail.com
 : Date: 19-6-10 下午3:50
 : LastModify: 19-5-15 下午5:58
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.common.base.author;


/**
 * 用户信息提供器
 *
 * @author <a href ="https://blog.janloong.com">Janloong Doo</a>
 * @version V1.0
 * @since 2019-05-15 17:54
 **/
public class AuthorInfoProvider {

    AuthorInfo authrorInfo;

    public AuthorInfo getAuthrorInfo() {
        return authrorInfo;
    }

    public void setAuthrorInfo(AuthorInfo authrorInfo) {
        this.authrorInfo = authrorInfo;
    }
}
