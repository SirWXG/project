package com.springboot.boot.controller;

import com.springboot.boot.pojo.Material;
import com.springboot.boot.service.MaterialService;
import com.springboot.boot.utils.SimpleResult;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    private MaterialService materialService;

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
                String array_item[] = array_index[k].split("<td style="+"\"width:10%\""+">");
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
        getPath(wb,xlsName);
        return result;
    }

    @RequestMapping(value = "/uploads",method = RequestMethod.POST,produces="application/json")
    @ResponseBody
    public String uploadFiles(@RequestParam(name = "xlsName")String xlsName, HttpSession session) throws IOException, InterruptedException {
        String projectId =(String)session.getAttribute("projectId");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("projectName",projectId);
        List<Material> list = materialService.selectMaterialByProject(map);
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("page1");
        int k=0;
        Row row = sheet.createRow((short)k);
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("材料名称");
        row.createCell(2).setCellValue("材料规格");
        row.createCell(3).setCellValue("材料单位");
        row.createCell(4).setCellValue("材料数量");
        row.createCell(5).setCellValue("材料购买价");
        row.createCell(6).setCellValue("合计");
        row.createCell(7).setCellValue("材料类型");
        row.createCell(8).setCellValue("供应商");
        row.createCell(9).setCellValue("备注");
        for(Material material:list){
            k++;
            row = sheet.createRow((short)k);
            row.createCell(0).setCellValue(material.getId());
            row.createCell(1).setCellValue(material.getMaterialName());
            row.createCell(2).setCellValue(material.getMaterialType());
            row.createCell(3).setCellValue(material.getMaterialUnit());
            row.createCell(4).setCellValue(material.getMaterialNum());
            row.createCell(5).setCellValue(material.getBuyPrice());
            row.createCell(6).setCellValue(material.getMaterialNum()*material.getBuyPrice());
            row.createCell(7).setCellValue(material.getMaterialVar());
            row.createCell(8).setCellValue(material.getMaterialSupplier());
            row.createCell(9).setCellValue(material.getNote());
        }
        getPath(wb,xlsName);
        return "";
    }

    public static void getPath(Workbook wb,String xlsName) throws IOException, InterruptedException {
        String path = "E:\\xls";
        File files = new File(path);
        if(!files.exists()){
            files.mkdirs();
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
    }
}
