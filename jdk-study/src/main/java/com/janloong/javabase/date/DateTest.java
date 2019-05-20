package com.janloong.javabase.date;


import java.util.Calendar;
import java.util.Date;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2018-07-12 16:56
 */
public class DateTest {

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date.toInstant());
        System.out.println(date.getTime());
        System.out.println(date.toString());

        System.out.println("============");
        Calendar instance = Calendar.getInstance();
        System.out.println(instance.getWeekYear());
        System.out.println(instance.getTime());
        System.out.println(instance.getFirstDayOfWeek());
        System.out.println(instance.get(Calendar.DAY_OF_WEEK));

    }
}
