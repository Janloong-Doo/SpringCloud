/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: PersonItemProcessor.java
 : Author: janloongdoo@gmail.com
 : Date: 19-7-5 下午2:22
 : LastModify: 19-7-5 上午11:05
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.batch.config;


import com.janloong.batch.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-07-05 11:03
 */
@Slf4j
public class PersonItemProcessor implements ItemProcessor<Person, String> {

    @Override
    public String process(Person person) throws Exception {
        String greeting = "Hello " + person.getName() + "! your address is "
                + person.getAddress() + ".";

        log.info("converting '{}' into '{}'", person, greeting);
        return greeting;
    }
}
