package com.carfinance.core.handler;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.*;

public class DocumentHandler {

    private Configuration configuration = null;

    public DocumentHandler() {
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
    }

    public File createDoc(String templatePath , String templateName , Map dataMap , String file_upload_path , String fileName) {
         /* // 要填入模本的数据文件
         Map dataMap = new HashMap();
         getData(dataMap);*/
        // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
        // ftl文件存放路径
        configuration.setClassForTemplateLoading(this.getClass() , templatePath);
        Template t = null;
        try {
            // test.ftl为要装载的模板
            t = configuration.getTemplate(templateName);
            t.setEncoding("utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 输出文档路径及名称
//        String root = System.getProperty("dhulims.root");
//        String upLoad = root+"upload"+File.separator;
////     File sendPath = new File(upLoad+projectNO);
//        File file = new File(upLoad);
        File file = new File(file_upload_path);
        if(!file.exists()){
            file.mkdirs();
        }

        File outFile = new File(file_upload_path + fileName + ".doc");
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        try {
            t.process(dataMap, out);
            /*out.close();*/
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outFile;
    }

//    public static void main(String templatePath, String templateName, Map dataMap,String fileName) {
//        new DocumentHandler().createDoc(templatePath,templateName,dataMap,fileName);
//    }
}
