/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: AuthorInfoProvider.java
 : Author: janloongdoo@gmail.com
 : Date: 19-5-15 下午5:54
 : LastModify: 19-5-15 下午5:54
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springbootstartercustom.entity;


/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-05-15 17:54
 */
public class AuthorInfoProvider {

    AuthorInfo authrorInfo;

    public AuthorInfo getAuthrorInfo() {
        return authrorInfo;
    }

    public void setAuthrorInfo(AuthorInfo authrorInfo) {
        this.authrorInfo = authrorInfo;
    }
}
