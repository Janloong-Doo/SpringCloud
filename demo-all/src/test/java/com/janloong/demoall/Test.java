/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: Test.java
 : Author: janloongdoo@gmail.com
 : Date: 19-2-28 下午2:33
 : LastModify: 19-2-28 下午2:33
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.demoall;


/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-02-28 14:33
 */
public class Test {

    public static void main(String[] args) {
        //    List<Map<String, Object>> list = new LinkedList<>();
        //    //    List<Map<String, Object>> result = new LinkedList<>();
        //    //    String[] strings = new String[]{"app", "cpu", "memory", "node",
        //    //            "disk", "net", "cpzl", "sjk", "clock"};
        //    //
        //    //    for (int i = 0; i < 9; i++) {
        //    //
        //    //        Map<String, Object> map = new HashMap<>();
        //    //        map.put("TYPEID", strings[i]);
        //    //        map.put("count", 0);
        //    //        result.add(i, map);
        //    //    }
        //    //    System.out.println("aaaa" + result.toString());
        //    //    list.forEach(m -> {
        //    //        m.forEach((s, o) -> {
        //    //            switch (o.toString()) {
        //    //                case "app_error":
        //    //                    result.get(0).put("count", m.get("count"));
        //    //                    break;
        //    //                case "cpu_over":
        //    //                    result.get(1).put("count", m.get("count"));
        //    //                    break;
        //    //                case "node_error":
        //    //                    result.get(2).put("count", m.get("count"));
        //    //                    break;
        //    //                case "disk_over":
        //    //                    result.get(3).put("count", m.get("count"));
        //    //                    break;
        //    //                case "net_state":
        //    //                    result.get(4).put("count", m.get("count"));
        //    //                    break;
        //    //                case "external_alarm":
        //    //                    result.get(5).put("count", m.get("count"));
        //    //                    result.get(6).put("count", m.get("count"));
        //    //                    result.get(7).put("count", m.get("count"));
        //    //                    break;
        //    //                case "clock_error":
        //    //                    result.get(8).put("count", m.get("count"));
        //    //                    break;
        //    //                case "yc_unrefresh":
        //    //                    break;
        //    //                case "proc_error":
        //    //                    break;
        //    //                default:
        //    //                    break;
        //    //            }
        //    //        });
        //    //    });
        //    //    System.out.println("bbbb" + result.toString());
        //for (int i = 0; i < 10; i++) {
        //
        //    boolean b = new Random().nextBoolean();
        //    System.out.println(b);
        //}
        //Map<String, Integer> map = new HashMap<>();
        //map.put("d", 4);
        //map.put("a", 5);
        //map.put("b", 2);
        //map.put("c", 3);
        //List<Map.Entry<String, Integer>> ll = new ArrayList<>(map.entrySet());
        //Collections.sort(ll, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        //HashMap<String, Integer> mm = new HashMap<>();
        //mm.put("total", 1);
        //Set<Map.Entry<String, Integer>> entries1 = mm.entrySet();
        //ll.add(0, entries1.iterator().next());
        //
        //System.out.println(map);
        //
        //System.out.println(ll);
        //List<Map.Entry<String, Integer>> entries = ll.subList(0, ll.size() - 2);
        //System.out.println(entries);
        //Integer i = 11;
        //Integer i2 = 3;
        //Double a = i.doubleValue()/i2.doubleValue();
        ////BigDecimal bigDecimal = BigDecimal.valueOf(a, 2);
        //DecimalFormat d = new DecimalFormat("0.00");
        //String format = d.format(a);
        //System.out.println(a);
        //System.out.println(format);
        ////System.out.println(bigDecimal);

        //Comparator<Integer> tComparator = (x, y) -> (x + y) * y;
        //int compare = tComparator.compare(1, 2);
        //System.out.println(compare);
        //
        //System.out.println();


        //int minute = LocalTime.now().getMinute();
        int minute = 2;
        int i = Math.floorMod(minute, 5);
        System.out.println("取模为：：");
        System.out.println(i);
    }
}
