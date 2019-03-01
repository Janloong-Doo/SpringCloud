/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: Test.java
 : Author: janloongdoo@gmail.com
 : Date: 19-2-28 下午2:33
 : LastModify: 19-2-28 下午2:33
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.demoall;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-02-28 14:33
 */
public class Test {

    public static void main(String[] args) {
        List<Map<String, Object>> list = new LinkedList<>();
        List<Map<String, Object>> result = new LinkedList<>();
        String[] strings = new String[]{"app", "cpu", "memory", "node",
                "disk", "net", "cpzl", "sjk", "clock"};

        for (int i = 0; i < 9; i++) {

            Map<String, Object> map = new HashMap<>();
            map.put("TYPEID", strings[i]);
            map.put("count", 0);
            result.add(i, map);
        }
        System.out.println("aaaa" + result.toString());
        list.forEach(m -> {
            m.forEach((s, o) -> {
                switch (o.toString()) {
                    case "app_error":
                        result.get(0).put("count", m.get("count"));
                        break;
                    case "cpu_over":
                        result.get(1).put("count", m.get("count"));
                        break;
                    case "node_error":
                        result.get(2).put("count", m.get("count"));
                        break;
                    case "disk_over":
                        result.get(3).put("count", m.get("count"));
                        break;
                    case "net_state":
                        result.get(4).put("count", m.get("count"));
                        break;
                    case "external_alarm":
                        result.get(5).put("count", m.get("count"));
                        result.get(6).put("count", m.get("count"));
                        result.get(7).put("count", m.get("count"));
                        break;
                    case "clock_error":
                        result.get(8).put("count", m.get("count"));
                        break;
                    case "yc_unrefresh":
                        break;
                    case "proc_error":
                        break;
                    default:
                        break;
                }
            });
        });
        System.out.println("bbbb" + result.toString());
    }
}
