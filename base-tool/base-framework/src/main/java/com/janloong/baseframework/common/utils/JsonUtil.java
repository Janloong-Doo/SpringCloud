/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2018  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: JsonUtil.java
 : Author: janloongdoo@gmail.com
 : Date: 18-12-5 下午5:54
 : LastModify: 18-11-26 下午5:38
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.baseframework.common.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.janloong.baseframework.common.enums.ResultEnum;
import com.janloong.baseframework.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-11-26 16:13
 */
@Slf4j
public class JsonUtil {


    public static void writeToFIle(String fileName, Object object) {

        WebApiResponse response = (WebApiResponse) object;
        if (!response.isSuccess()) {
            throw new BusinessException(ResultEnum.未知错误);
        }
        String s1 = JSON.toJSONString(response);
        Object data = response.getData();
        if (data instanceof List) {
            List list = (List) data;
            JSONArray jsonArray = new JSONArray(list);
            String s = jsonArray.toJSONString();
            File file = new File(fileName);
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(fileName, true);
                log.info("写入json文件内容为:" + s);
                //fileWriter.write(s);
                fileWriter.write(s1);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new BusinessException(ResultEnum.文件写入失败);
            }

        } else if (data instanceof Map) {

        }
    }

}
