package com.janloong.javabase.test;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-09-04 19:07
 */
public class Test {

    public static void main(String[] args) {
        info("1", "2019-09-02", "2019-09-04");
    }

    public static List<Object> info(String bzIdList, String startTime, String endTime) {
        //获取班组信息列表
        String bzSql = "select id,dept_name,SHOW_NAME_JSON from cm_dept where node_type=1 and id in ?";
        //List<Map<String, Object>> queryBzList = this.execSql(bzSql, bzIdList);
        List<Map<String, Object>> queryBzList = bzdate();
        List<String> idList = Arrays.asList(bzIdList.split(","));
        //获取工作类型基本信息
        String workTypeSql = "select * from cm_dictionary where dict_type='工作类型' order by dict_sort";
        //List<Map<String, Object>> workTypeList = execSql(workTypeSql);
        List<Map<String, Object>> workTypeList = workType();
        List<String> lables = workTypeList.stream().map(stringObjectMap -> String.valueOf(stringObjectMap.get("LABLE"))).collect(Collectors.toList());

        //数据查詢
        //List<List<Map<String, Object>>> queryList = new LinkedList<>();
        //Map<String, Map<Object, List<Map<String, Object>>>> queryMap = new HashMap<>();
        Map<String, Map<String, Map<String, List<Map<String, Object>>>>> queryMap = new HashMap<>();
        //根据班组信息查询数据库
        queryBzList.forEach(s -> {
            String dataSql = "select s1.WORK_TYPE,to_char(s1.CREATE_TIME,'yyyy-mm-dd') as time, listagg(USER_NAME, ',') within group ( order by USER_NAME) as names\n" +
                    "from STAFF_DETAIL s1\n" +
                    "         left join STAFF_WORK_RECORD s2\n" +
                    "                   on\n" +
                    "                           s1.WORK_ID = s2.ID\n" +
                    "                           and\n" +
                    "                           s2.DEPT_ID = ?\n" +
                    "where s1.CREATE_TIME between to_date(?, 'yyyy-mm-dd hh24:mi:ss') and to_date(?, 'yyyy-mm-dd hh24:mi:ss')\n" +
                    "group by WORK_TYPE,s1.CREATE_TIME order by s1.CREATE_TIME desc";
            //List<Map<String, Object>> maps1 = this.execSql(dataSql, s.get("ID"), startTime, endTime);
            List<Map<String, Object>> maps1 = new ArrayList<Map<String, Object>>(10);
            //maps1.add();
            //按照时间作一个区分
            Map<String, List<Map<String, Object>>> times = maps1.stream().collect(Collectors.groupingBy(map -> String.valueOf(map.get("TIMES"))));
            Map<String, Map<String, List<Map<String, Object>>>> mapd = new HashMap<>();
            times.forEach((o, maps) -> {
                //在时间区分的基础上再按照工作类型区分
                Map<String, List<Map<String, Object>>> work_type = maps.stream().collect(Collectors.groupingBy(stringObjectMap -> String.valueOf(stringObjectMap.get("WORK_TYPE"))));
                mapd.put(o, work_type);
            });
            //queryList.add(maps1);
            //queryMap.put(String.valueOf(s.get("ID")), times);
            //queryMap.put(String.valueOf(s.get("DEPT_NAME")), times);
            queryMap.put(String.valueOf(s.get("DEPT_NAME")), mapd);
        });
        LocalDate startTimeLocal = LocalDate.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endTimeLocal = LocalDate.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //List<LocalDate> dateList = BaseUtil.getDate(startTimeLocal, endTimeLocal, "day");
        List<LocalDate> dateList = null;
        Collections.reverse(dateList);
        List<Object> result = new LinkedList<>();
        dateList.forEach(localDate -> {

            //按照班组类别划分取查询结果
            queryBzList.forEach(s -> {
                Map<String, Object> singResult = new HashMap<>();
                //Map<Object, List<Map<String, Object>>> dept_name = queryMap.get(s.get("DEPT_NAME"));
                Map<String, Map<String, List<Map<String, Object>>>> deptName = queryMap.get(s.get("DEPT_NAME"));
                if (deptName != null) {

                    //获取当前日期的数据
                    Map<String, List<Map<String, Object>>> objectListMap = deptName.get(localDate.toString());
                    //遍历worktype
                    lables.forEach(s1 -> {
                        //获取当前workType的数据
                        List<Map<String, Object>> t1 = objectListMap.get(s1);
                        String names = t1 != null && t1.size() > 0 ? String.valueOf(t1.get(0).get("NAMES")) : "";
                        //objectListMap.get("t1");
                        singResult.put(s1, names);
                    });
                }

                singResult.put("time", localDate.toString());
                singResult.put("DEPT_NAME", s.get("DEPT_NAME"));

                //天为单位插入日期对应的班组信息集合
                result.add(singResult);
            });
        });
        return result;
    }

    private static List<Map<String, Object>> workType() {


        return null;
    }

    private static List<Map<String, Object>> bzdate() {

        return null;
    }


}
