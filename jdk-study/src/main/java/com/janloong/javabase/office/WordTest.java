/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: WordTest.java
 : Author: janloongdoo@gmail.com
 : Date: 2019/9/20 上午9:44
 : LastModify: 2019/9/20 上午9:44
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.javabase.office;


import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-09-20 9:44
 */
public class WordTest {

    public static void main(String[] args) throws Exception {
        String templatePath = "D:\\work/cache/123.doc";
        Map<String, Object> map_data = new HashMap<>();
        map_data.put("project_id", "2019.21");
        //map_data.put("project_name", "ZJIC");
        //map_data.put("depth", "10.22");
        //map_data.put("hole_id", "ZKS12");
        //map_data.put("hole_altitude", "100");
        //map_data.put("hole_mileage", "23.21");
        //map_data.put("endhole_depth", "43");
        ArrayList<Map<String, String>> list_data = new ArrayList<>();
        Map<String, String> temp = new HashMap<>();
        //for (int i = 0; i < 10; i++) {
        //    temp = new HashMap<>();
        //    temp.put("layer_id", i + "");
        //    temp.put("start_depth", "start_depth");
        //    temp.put("end_depth", "end_depth");
        //    temp.put("geotechnical_name", "geotechnical_name");
        //    temp.put("geotechnical_description", "geotechnical_description");
        //    temp.put("sample_id", "sample_id");
        //    temp.put("sample_depth", "sample_depth");
        //    list_data.add(temp);
        //}
        File file = new File("D:\\work/cache/test.doc");
        FileOutputStream out = new FileOutputStream(file);
        Export2GeotechnicalLayeringTable(map_data, list_data, templatePath, out);
    }

    public static boolean Export2GeotechnicalLayeringTable(Map<String, Object> map_data, ArrayList<Map<String, String>> list_data, String templatePath, OutputStream out) throws Exception {
        boolean result = false;
        FileInputStream in = null;
        HWPFDocument document = null;
        try {
            in = new FileInputStream(templatePath);
            //InputStream nonClosingInputStream = POIFSFileSystem.createNonClosingInputStream(in);
            //POIFSFileSystem poifsFileSystem = new POIFSFileSystem();
            //poifsFileSystem.createDocument(in,"document-doo");
            //document = new HWPFDocument(poifsFileSystem);
            document = new HWPFDocument(in);
            Range range = document.getRange();
            range.replaceText("${USERNAME}", map_data.get("project_id").toString());  //
            //range.replaceText("${project_name}", map_data.get("project_name").toString());  //
            //range.replaceText("${depth}", map_data.get("depth").toString());  //
            //range.replaceText("${hole_id}", map_data.get("hole_id").toString());  //
            //range.replaceText("${hole_altitude}", map_data.get("hole_altitude").toString());  //
            //range.replaceText("${hole_mileage}", map_data.get("hole_mileage").toString());  //
            //range.replaceText("${endhole_depth}", map_data.get("endhole_depth").toString());  //
            //写入表格数据
            //遍历range范围内的table。
            //TableIterator tableIter = new TableIterator(range);
            //Table table;
            //TableRow row;
            //while (tableIter.hasNext()) {
            //    table = tableIter.next();
            //    int rowNum = table.numRows();
            //    for (int i = 0, j = 2; i < list_data.size() && j < rowNum; i++, j++) {
            //        row = table.getRow(j);
            //        TableCell cell = row.getCell(0);
            //        //CharacterRun characterRun = cell.insertBefore("");
            //        //cell.insertBefore(list_data.get(i).get("layer_id"));
            //        //row.getCell(0).insertBefore(list_data.get(i).get("layer_id"));
            //        //row.getCell(1).insertBefore(list_data.get(i).get("start_depth"));
            //        //row.getCell(2).insertBefore(list_data.get(i).get("end_depth"));
            //        //row.getCell(3).insertBefore(list_data.get(i).get("geotechnical_name"));
            //        //row.getCell(4).insertBefore(list_data.get(i).get("geotechnical_description"));
            //        //row.getCell(5).insertBefore(list_data.get(i).get("sample_id"));
            //        //row.getCell(6).insertBefore(list_data.get(i).get("sample_depth"));
            //    }
            //}
            document.write(out);
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
}
