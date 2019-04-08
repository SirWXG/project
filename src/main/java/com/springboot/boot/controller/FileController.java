package com.springboot.boot.controller;

import com.springboot.boot.utils.SimpleResult;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


@Controller
@RequestMapping(value = "/file")
public class FileController {

    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST,produces="application/json")
    @ResponseBody
    public SimpleResult getFile(@RequestParam(name = "excel") String excel,@RequestParam(name = "xlsName")String xlsName) throws IOException, InterruptedException {
        SimpleResult result = new SimpleResult();
        String array_index[] = excel.split("<tr>");
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("page1");
        Row row = null;
        for(int k=1;k<array_index.length;k++){
            row = sheet.createRow((short)k-1);
            if(k>1){
                String array_item[] = array_index[k].split("<td>");
                for(int i=0;i<array_index.length-1;i++){
                    for(int m=0;m<array_item.length;m++){
                        if(array_item[m].indexOf("<")>0){
                            row.createCell(m).setCellValue(array_item[m].substring(0,array_item[m].indexOf("<")));
                        }
                    }
                }
            }else {
                String array_titile[] = array_index[k].split("<th>");
                for(int i=0;i<array_titile.length;i++){
                    if(array_titile[i].indexOf("<")>0){
                        row.createCell(i).setCellValue(array_titile[i].substring(0,array_titile[i].indexOf("<")));
                    }
                }
            }
        }
        String filePath = "E:\\xls\\"+xlsName+".xls";
        File file = new File(filePath);
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream out = new FileOutputStream(file);
        wb.write(out);
        final Runtime runtime = Runtime.getRuntime();
        Process process = null;
        final String cmd = "rundll32 url.dll FileProtocolHandler file://"+filePath;
        Thread.sleep(2000);
        process = runtime.exec(cmd);
        out.close();
        return result;
    }
}
