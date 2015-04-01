package com.carfinance.module.common.controller;

import com.carfinance.core.handler.DocumentHandler;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.log.service.LogService;
import com.carfinance.module.login.service.LoginService;
import com.carfinance.module.vehicleservicemanage.domain.PropertyContraceInfo;
import com.carfinance.module.vehicleservicemanage.domain.VehicleContraceInfo;
import com.carfinance.module.vehicleservicemanage.service.VehicleServiceManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/downloaddocument")
public class DocumentDownloadController {
	final Logger logger = LoggerFactory.getLogger(DocumentDownloadController.class);

    @Autowired
    private Properties appProps;
    @Autowired
    private VehicleServiceManageService vehicleServiceManageService;

//    @RequestMapping(value = "/getcontrace" , method = RequestMethod.GET)
//    public void allVehicles(Model model , HttpServletRequest request , HttpServletResponse response) {
//        String contrace_id_str = request.getParameter("contrace_id");
//        long contrace_type = Long.valueOf(request.getParameter("contrace_type"));//合同类型，1-零租；2-产权租
//        response.setContentType("application/doc;charset=UTF-8");
//        byte b[] = new byte[1024];
//        //转换startDate的时间格式；
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        String org_id = "";
//        if(contrace_type == 1) {
//            VehicleContraceInfo vehicleContraceInfo = this.vehicleServiceManageService.getVehicleContraceInfoById(Long.valueOf(contrace_id_str));
//            if(vehicleContraceInfo != null) {
//                org_id = String.valueOf(vehicleContraceInfo.getOrg_id());
//            }
//        } else if(contrace_type == 2) {
//            PropertyContraceInfo propertyContraceInfo = this.vehicleServiceManageService.getPropertyContraceInfoById(Long.valueOf(contrace_id_str));
//            if(propertyContraceInfo != null) {
//                org_id = String.valueOf(propertyContraceInfo.getOrg_id());
//            }
//        }
//
//        //设置word变量值
//        Map<String , Object> dataMap = new HashMap<String , Object>();
////        dataMap.put("departName","aaaa");
//        dataMap.put("author", "张三");
//        dataMap.put("remark", "这是测试备注信息");
//
//
//
//        //生成word
//        DocumentHandler handler = new DocumentHandler();
//        String file_upload_path = appProps.get("conrace.download.path").toString().replace("${org_id}", org_id).replace("${contrace_id}",contrace_id_str);
//        File outFile = handler.createDoc("/com/carfinance/template", "test.ftl", dataMap , file_upload_path , "text");
//
//        //下面的代码是导出word
//        FileInputStream in = null;
//        OutputStream o = null;
//
//        try {
//            in = new FileInputStream(outFile);
//            o = response.getOutputStream();
//            response.setContentType("application/x-tar");
//            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("text.doc", "UTF-8"));// 指定下载的文件名
//            response.setHeader("Content_Length",String.valueOf( outFile.length()));       // download the file.
//            int n = 0;
//            while ((n = in.read (b))!= -1) {
//                o.write(b, 0, n);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                in.close();
//                o.flush();
//                o.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @RequestMapping(value = "/getcontrace" , method = RequestMethod.GET)
    public void allVehicles(Model model , HttpServletRequest request , HttpServletResponse response) {
        String contrace_id_str = request.getParameter("contrace_id");
        long contrace_type = Long.valueOf(request.getParameter("contrace_type"));//合同类型，1-零租；2-产权租
        response.setContentType("application/doc;charset=UTF-8");
        byte b[] = new byte[1024];
        //转换startDate的时间格式；
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String org_id = "";
        if(contrace_type == 1) {
            VehicleContraceInfo vehicleContraceInfo = this.vehicleServiceManageService.getVehicleContraceInfoById(Long.valueOf(contrace_id_str));
            if(vehicleContraceInfo != null) {
                org_id = String.valueOf(vehicleContraceInfo.getOrg_id());
            }
        } else if(contrace_type == 2) {
            PropertyContraceInfo propertyContraceInfo = this.vehicleServiceManageService.getPropertyContraceInfoById(Long.valueOf(contrace_id_str));
            if(propertyContraceInfo != null) {
                org_id = String.valueOf(propertyContraceInfo.getOrg_id());
            }
        }

        //设置word变量值
        Map<String , Object> dataMap = new HashMap<String , Object>();
//        dataMap.put("departName","aaaa");
        dataMap.put("author", "张三");
        dataMap.put("remark", "这是测试备注信息");



        //生成word
        DocumentHandler handler = new DocumentHandler();
        String file_upload_path = appProps.get("conrace.download.path").toString().replace("${org_id}", org_id).replace("${contrace_id}",contrace_id_str);
        File outFile = handler.createDoc("/com/carfinance/template", "test.ftl", dataMap , file_upload_path , "text");

        //下面的代码是导出word
        FileInputStream in = null;
        OutputStream o = null;

        try {
            in = new FileInputStream(outFile);
            o = response.getOutputStream();
            response.setContentType("application/x-tar");
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("text.doc", "UTF-8"));// 指定下载的文件名
            response.setHeader("Content_Length",String.valueOf( outFile.length()));       // download the file.
            int n = 0;
            while ((n = in.read (b))!= -1) {
                o.write(b, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                o.flush();
                o.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}