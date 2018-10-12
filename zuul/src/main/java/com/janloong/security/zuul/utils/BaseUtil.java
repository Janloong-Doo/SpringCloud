/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: BaseUtil.java
 : Author: janloongdoo@gmail.com
 : Date: 18-9-26 下午3:48
 : LastModify: 18-9-26 下午3:48
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.security.zuul.utils;


import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-09-26 15:48
 */
public class BaseUtil {

    /**
     * Str 2 list list.
     *
     * @param str the str
     * @return the list
     */
    public static List<String> str2List(String str) {
        return str2List(str, ",");
    }

    /**
     * String转换为集合
     *
     * @param str   the str
     * @param regex the regex
     * @return the list
     * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
     * @date 2018 /5/21 14:50
     */
    public static List<String> str2List(String str, String regex) {
        if (StringUtils.isBlank(str)) {
            return new ArrayList<>();
        }
        String[] split = str.split(regex);
        List<String> list = Arrays.asList(split);
        return list;
    }
}
