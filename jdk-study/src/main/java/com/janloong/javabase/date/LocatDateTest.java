package com.janloong.javabase.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Janloong
 * @create 2017-06-17 下午4:53
 **/
public class LocatDateTest {

    public static void main(String[] args) {
        //日期时间
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println(localDateTime);
        //System.out.println(localDateTime.);
        String startTime = "2018-07-12 17:20:58";
        System.out.println(LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("========");

        System.out.println("==============localdatetime==================");
        // 获取今天周几
        DayOfWeek from = LocalDate.now().getDayOfWeek();
        System.out.println(from.getValue());

        //WeekFields.of()
        //当前日期月内的第一个周一
        DayOfWeek monday = DayOfWeek.MONDAY;
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.dayOfWeekInMonth(1, monday);
        LocalDate with = LocalDate.now().with(temporalAdjuster);
        System.out.println(with);

        //获取上周日的日期
        TemporalAdjuster temporalAdjuster2 = TemporalAdjusters.previous(DayOfWeek.SUNDAY);
        LocalDate with2 = LocalDate.now().with(temporalAdjuster2);
        System.out.println(with2);

        //System.out.println();
        //LocalDateTime from2 = LocalDateTime.from(DayOfWeek.MONDAY);
        //System.out.println(from2);
        System.out.println("==============dayofweek==================");
        //获取本周几的日期
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        System.out.println(cal.getTime());

        System.out.println("==============Calendar==================");
        Instant instant = Instant.now();
        System.out.println(instant);
        //线程池
        ThreadLocalRandom current = ThreadLocalRandom.current();
        System.out.println(current);
        System.out.println("========");
        System.out.println(Calendar.getInstance().getTime());

        System.out.println("====遍历时间====");
        List<LocalDate> date = getDate(LocalDate.now().minusMonths(1), LocalDate.now(), "day");
        System.out.println(date);

        List<LocalDate> month = getDate(LocalDate.now().withMonth(1), LocalDate.now().withMonth(12), "month");

        System.out.println(month);

        System.out.println("====月份====");
        YearMonth now = YearMonth.now();
        System.out.println(now);

        //LocalDate from1 = LocalDate.from(now);
        //System.out.println(from1);

        //DateTimeFormatter.ofPattern("yyyy-mm");
        //DateTimeFormatter.ISO_DATE;
        LocalDate localDate = LocalDate.now().withMonth(1);
        String s = "2018-07";
        YearMonth parse1 = YearMonth.parse(s);
        //LocalDate parse = LocalDate.parse("2018-07",DateTimeFormatter.ofPattern("yyyy-mm"));

        System.out.println(parse1);
        //.format(DateTimeFormatter.ofPattern("yyyy-mm")));
    }


    public static List<LocalDate> getDate(LocalDate start, LocalDate end, String type) {
        List<LocalDate> linkedList = new LinkedList<>();
        LocalDate startCache = start;
        while (startCache.isBefore(end)) {
            linkedList.add(startCache);
            switch (type) {
                case "year":
                    startCache = startCache.plusYears(1);
                    break;
                case "month":
                    startCache = startCache.plusMonths(1);
                    break;
                case "week":
                    startCache = startCache.plusWeeks(1);
                    break;
                case "day":
                    startCache = startCache.plusDays(1);
                    break;
                default:
                    //throw new BusinessException(ResultEnum.参数错误);
                    break;
            }
        }
        linkedList.add(end);
        return linkedList;
    }
}
