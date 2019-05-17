/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: AuthorInfo.java
 : Author: janloongdoo@gmail.com
 : Date: 19-5-15 上午11:55
 : LastModify: 19-5-15 上午11:55
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.springbootstartercustom.entity;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-05-15 11:55
 */
@ConfigurationProperties(prefix = "author")
public class AuthorInfo {

    private static final String NAME = "janloongdoo";
    private static final String DOMAIN = "https://github.com/Janloong-Doo";
    private static final String DESCRIPTION = "You have to work very hard to look effortless.";
    private String name = NAME;
    private String domain = DOMAIN;
    private String description = DESCRIPTION;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
