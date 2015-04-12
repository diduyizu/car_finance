package com.carfinance.module.common.controller;

import com.carfinance.core.handler.DocumentHandler;
import com.carfinance.module.vehiclemanage.domain.VehicleInfo;
import com.carfinance.module.vehiclemanage.service.VehicleManageService;
import com.carfinance.module.vehicleservicemanage.domain.PropertyContraceInfo;
import com.carfinance.module.vehicleservicemanage.domain.VehicleContraceInfo;
import com.carfinance.module.vehicleservicemanage.domain.VehicleContraceVehsInfo;
import com.carfinance.module.vehicleservicemanage.service.VehicleServiceManageService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


@Controller
@RequestMapping("/downloaddocument")
public class DocumentDownloadController {
	final Logger logger = LoggerFactory.getLogger(DocumentDownloadController.class);

    @Autowired
    private Properties appProps;
    @Autowired
    private VehicleServiceManageService vehicleServiceManageService;
    @Autowired
    private VehicleManageService vehicleManageService;

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

    /**
     * 合同生成PDF
     * @param model
     * @param request
     * @param response
     */
    @RequestMapping(value = "/pdfcontrace" , method = RequestMethod.GET)
    public void pdfContrace(Model model , HttpServletRequest request , HttpServletResponse response) {
        String contrace_id_str = request.getParameter("contrace_id");
        long contrace_type = Long.valueOf(request.getParameter("contrace_type"));//合同类型，1-零租；2-产权租

        String org_id = "";
        String contrace_no = "";
        String customer_name = "";
        String license_plate = "";
        String vehicle_model = "";
        String engine_no = "";
        String carframe_no = "";
        double guide_price = 0;
        String color = "";
        String begin_time = "";
        String end_time = "";
        String driving_user_name = "";
        String driving_user_license_no = "";
        String daily_price = "";
        String daily_available_km = "";
        String over_km_price = "";
        String over_hour_price = "";
        String month_price = "";
        String month_available_km = "";
        String pre_payment = "";
        String deposit = "";
        String monthly_day = "";


        if(contrace_type == 1) {
            VehicleContraceInfo vehicleContraceInfo = this.vehicleServiceManageService.getVehicleContraceInfoById(Long.valueOf(contrace_id_str));
            if(vehicleContraceInfo != null) {
                org_id = String.valueOf(vehicleContraceInfo.getOrg_id());
                contrace_no = vehicleContraceInfo.getContrace_no();
                customer_name = vehicleContraceInfo.getCustomer_name();

                List<VehicleContraceVehsInfo> vehsList = this.vehicleServiceManageService.getVehicleContraceVehsListByContraceId(vehicleContraceInfo.getId());
                if(vehsList != null) {
                    VehicleContraceVehsInfo vehsInfo = vehsList.get(0);
                    VehicleInfo vehicleInfo = this.vehicleManageService.getVehicleInfoByid(vehsInfo.getVehicle_id());

                    license_plate = vehicleInfo.getLicense_plate();
                    vehicle_model = vehicleInfo.getModel();
                    engine_no = vehicleInfo.getEngine_no();
                    carframe_no = vehicleInfo.getCarframe_no();
                    guide_price = vehicleInfo.getGuide_price();
                    color = vehicleInfo.getColor();

                    driving_user_name = vehsInfo.getDriving_user_name();
                    driving_user_license_no = vehsInfo.getDriving_user_license_no();

                    daily_price = vehicleInfo.getDaily_price()+"";
                }


                begin_time = vehicleContraceInfo.getUse_begin();
                end_time = vehicleContraceInfo.getUse_end();

                daily_available_km = vehicleContraceInfo.getDaily_available_km()+"";
                over_km_price = vehicleContraceInfo.getOver_km_price()+"";
                over_hour_price = vehicleContraceInfo.getOver_hour_price()+"";
                month_price = vehicleContraceInfo.getMonth_price()+"";
                month_available_km = vehicleContraceInfo.getMonth_available_km()+"";
                pre_payment = vehicleContraceInfo.getPre_payment()+"";
                deposit = vehicleContraceInfo.getDeposit()+"";
                monthly_day = vehicleContraceInfo.getMonthly_day()+"";
            }
        } else if(contrace_type == 2) {
            PropertyContraceInfo propertyContraceInfo = this.vehicleServiceManageService.getPropertyContraceInfoById(Long.valueOf(contrace_id_str));
            if(propertyContraceInfo != null) {
                org_id = String.valueOf(propertyContraceInfo.getOrg_id());
                contrace_no = propertyContraceInfo.getContrace_no();
                customer_name = propertyContraceInfo.getCustomer_name();
            }
        }

        Document pdfDoc = new Document(PageSize.A4, 50, 50, 50, 50);
        // 将要生成的 pdf 文件的路径输出流
        try {
            BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);

            Font bold_fontChinese = new Font(bfChinese, 18, Font.BOLD, BaseColor.BLACK);
            Font normal_fontChinese = new Font(bfChinese, 12, Font.NORMAL, BaseColor.BLACK);
            Font normal_desc_fontChinese = new Font(bfChinese, 6, Font.NORMAL, BaseColor.BLACK);


            FileOutputStream pdfFile = new FileOutputStream(new File("D:\\my_project\\myworkfirstPdf.pdf"));
            // pdf 文件中的一个文字段落
            Paragraph paragraph1 = new Paragraph("汽车租赁合同" , bold_fontChinese);
            paragraph1.setAlignment(Element.ALIGN_CENTER);


            Chunk customer_name_underline = new Chunk(customer_name);
            customer_name_underline.setUnderline(1f, 3f);

            Chunk license_plate_underline = new Chunk(license_plate);
            license_plate_underline.setUnderline(1f, 3f);

            Chunk vehicle_model_underline = new Chunk(vehicle_model);
            vehicle_model_underline.setUnderline(1f, 3f);

            Chunk engine_no_underline = new Chunk(engine_no);
            engine_no_underline.setUnderline(1f, 3f);

            Chunk carframe_no_underline = new Chunk(carframe_no);
            carframe_no_underline.setUnderline(1f, 3f);

            Chunk guide_price_underline = new Chunk(guide_price+"");
            guide_price_underline.setUnderline(1f, 3f);

            Chunk color_underline = new Chunk(color);
            color_underline.setUnderline(1f, 3f);

            Chunk begin_time_underline = new Chunk(begin_time);
            begin_time_underline.setUnderline(1f, 3f);

            Chunk end_time_underline = new Chunk(end_time);
            end_time_underline.setUnderline(1f, 3f);

            Chunk driving_user_name_underline = new Chunk(driving_user_name==null ? "___/___":driving_user_name);
            driving_user_name_underline.setUnderline(1f, 3f);

            Chunk driving_user_license_no_underline = new Chunk(driving_user_license_no == null ? "_____/_____":driving_user_license_no);
            driving_user_license_no_underline.setUnderline(1f, 3f);

            Chunk daily_price_underline = new Chunk(daily_price);
            daily_price_underline.setUnderline(1f, 3f);

            Chunk daily_available_km_underline = new Chunk(daily_available_km);
            daily_available_km_underline.setUnderline(1f, 3f);

            Chunk over_km_price_underline = new Chunk(over_km_price);
            over_km_price_underline.setUnderline(1f, 3f);

            Chunk over_hour_price_underline = new Chunk(over_hour_price);
            over_hour_price_underline.setUnderline(1f, 3f);

            Chunk month_price_underline = new Chunk(month_price == null ? "_____/_____":month_price);
            month_price_underline.setUnderline(1f, 3f);

            Chunk month_available_km_underline = new Chunk(month_available_km==null ? "_____/_____":month_available_km);
            month_available_km_underline.setUnderline(1f, 3f);

            Chunk pre_payment_underline = new Chunk(pre_payment);
            pre_payment_underline.setUnderline(1f, 3f);

            Chunk deposit_underline = new Chunk(deposit);
            deposit_underline.setUnderline(1f, 3f);

            Chunk monthly_day_underline = new Chunk(monthly_day==null ? "___/___":monthly_day);
            monthly_day_underline.setUnderline(1f, 3f);


            Paragraph paragraph2 = new Paragraph("出租方：扬州瑞特汽车服务有限公司（简称：甲方）    合同编号："+contrace_no , normal_fontChinese);
            Paragraph paragraph3 = new Paragraph("承租方："+customer_name_underline+" (简称：乙方）    签约时间：20____年____月____日" , normal_fontChinese);
            Paragraph paragraph4 = new Paragraph("根据我国《合同法》 、《担保法》及有关规定，为了明确甲方与乙方权利义务经双方协商一致，签订本合同。" , normal_fontChinese);
            Paragraph paragraph5 = new Paragraph("一、租赁汽车壹辆，车牌号："+license_plate_underline+"  车型号："+vehicle_model_underline+"  颜色："+color_underline+"  发动机号："+engine_no_underline+"  底盘号："+carframe_no_underline+"  重置价人民币"+guide_price_underline+" 万元。" , normal_fontChinese);
            Paragraph paragraph6 = new Paragraph("二、甲方从 "+begin_time_underline+" 起将租赁汽车交付乙方使用，至 "+end_time_underline+" 收回。" , normal_fontChinese);
            Paragraph paragraph7 = new Paragraph("三、甲乙双方商定由驾驶员 "+driving_user_name_underline+" 驾驶证号"+driving_user_license_no_underline+" 专人驾驶租赁汽车。" , normal_fontChinese);
            Paragraph paragraph8 = new Paragraph("四、租金和租金缴纳方式及期限" , normal_fontChinese);
            Paragraph paragraph9 = new Paragraph("1、租赁期内每辆车每天租金 "+daily_price_underline+" 元。每天可用 "+daily_available_km_underline+" 公里。每超一公里加收人民币 "+over_km_price_underline+" 元。每超一小时加收 "+over_hour_price_underline+" 元。起租按天计算，不足一天按一天计算租金。包月车辆按每月30天计算，包月价为每月人民币为每月人民币 "+month_price_underline+" 元，每月可用 "+month_available_km_underline+" 公里。超出30天的部分按日租算，超公里部分按所超公里数加收租金。" , normal_fontChinese);
            Paragraph paragraph10 = new Paragraph("2、办理租赁，还车手续须在甲方营业时间内。营业时间为每天：8:00---20:00。" , normal_fontChinese);
            Paragraph paragraph11 = new Paragraph("3、租金可以现金或者承兑汇票交付，使用承兑汇票须待款到甲方账户后，乙方方可提车。" , normal_fontChinese);
            Paragraph paragraph12 = new Paragraph("4、租金于本合同生效之日先预付人民币 "+pre_payment_underline+" 元，于还车之日结清。租赁期在一个月以上的，于每月 "+monthly_day_underline+" 日（节假日顺延）结算支付一次。" , normal_fontChinese);
            Paragraph paragraph13 = new Paragraph("5、乙方租赁甲方的汽车，须付押金人民币 "+deposit_underline+" 元，(含违章押金人民贰千元)于还车之日结清。租赁期满无其它意外情况，由甲方不计息退还乙方。违章押金在还车两个月后，查询用车期间无违章时退还。" , normal_fontChinese);
            Paragraph paragraph14 = new Paragraph("6、对租金、修理及其他费用，乙方逾期支付，逾期款除按1%每日计算滞纳金外，乙方于逾期支付日起，无条件地将租赁汽车立即送还甲方。否则由此造成的一起后果由乙方全部承担。" , normal_fontChinese);
            Image image = Image.getInstance("D:\\my_project\\chekuang.jpg");


            Paragraph paragraph15 = new Paragraph("五、甲方责任和义务" , normal_desc_fontChinese);
            Paragraph paragraph16 = new Paragraph("1、保证租赁车辆出租时性能良好、备胎、随车工具等附件齐全有效，并与乙方书面交接清楚。" , normal_desc_fontChinese);
            Paragraph paragraph17 = new Paragraph("2、负责对租赁汽车进行正常维修及定期保养，车辆的间隔里程为5000公里，正负不得超过200公里。" , normal_desc_fontChinese);
            Paragraph paragraph18 = new Paragraph("3、负责对租赁汽车在扬州市区境内非人为路抛的急修，乙方承担相应的服务费用。" , normal_desc_fontChinese);
            Paragraph paragraph19 = new Paragraph("4、负责租赁汽车的保险投保，险种包括：□强制险、□车损险、□三责险、□车上人员险、□盗抢险、□玻璃破碎险，详见随车保卡载明。" , normal_desc_fontChinese);
            Paragraph paragraph20 = new Paragraph("5、协助乙方处理使用租赁汽车期间发生的交通事故和按保险公司规定办理有关的索赔手续。" , normal_desc_fontChinese);
            Paragraph paragraph21 = new Paragraph("6、甲方根据乙方需要可配备驾驶员一至两名，向乙方提供有偿服务。" , normal_desc_fontChinese);
            Paragraph paragraph22 = new Paragraph("六、乙方的责任和义务" , normal_desc_fontChinese);
            Paragraph paragraph23 = new Paragraph("1、乙方的法定证件、公章及相关文件必须真实有效。伪造，失效或者冒用的，由乙方对所产生的一切经济后果及法律责任负全部责任。" , normal_desc_fontChinese);
            Paragraph paragraph24 = new Paragraph("2、乙方应该认真了解、检查和掌握租赁汽车的各种性能及操作方法。凡因乙方使用、操作不当或保管不善造成租赁汽车损坏及附件遗失、损坏的，由乙方负责赔偿。" , normal_desc_fontChinese);
            Paragraph paragraph25 = new Paragraph("3、乙方使用租赁汽车发生各类机件故障及事故车修复，须到甲方指定修理厂，不得自行送外厂修理。归还车辆时，经检验因乙方自行送外厂修理，换了非正宗零部件，车辆修理需重新进行的，乙方承担因此造成的修理费用和重新修复的搁车损失费用。" , normal_desc_fontChinese);
            Paragraph paragraph26 = new Paragraph("4、乙方使用租赁汽车发生交通事故，应立即向交管部门、当地保险机构和甲方报险，并保护好现场，采取必要的合理措施，防止或者减少损失。特别通知的，应当按照甲方或者保险机构通知的要求处理。" , normal_desc_fontChinese);
            Paragraph paragraph27 = new Paragraph("5、乙方使用租赁汽车发生事故，必须如实填写事故报告单。事故处理结束次日起三日内，乙方须将处理机关的处理结果及证明材料送交甲方，以利甲方处理保险理赔手续。因乙方原因而无法向保险公司索赔的，全部损失由乙方承担。" , normal_desc_fontChinese);
            Paragraph paragraph28 = new Paragraph("6、车辆发生损坏或发生交通事故，由甲方向保险公司索赔。索赔款赔付前，修理费及处理事故，医疗等费用由乙方先支付。保险公司赔偿后，由甲方领取赔偿金返还给乙方，但乙方须按车辆修理费（与事故责任无关）以如下比例赔甲方：修理费用在6万元以下按20%补偿，六万元以上的按30%补偿。保险赔偿金不足以弥补损失时，差额部分由乙方承担全部责任。事故造成车辆报废的，乙方按重置价向甲方赔偿。造成破坏的，乙方向甲方承担租赁汽车从损坏至修复上路期间的搁车损失。" , normal_desc_fontChinese);
            Paragraph paragraph29 = new Paragraph("7、乙方自备驾驶员，须持法定有效的机动车驾驶证件，并有一年以上的实际驾驶经历。非本合同注明的驾驶员无权驾驶租赁汽车，否则一切后果由乙方负责。" , normal_desc_fontChinese);
            Paragraph paragraph30 = new Paragraph("8、乙方驾驶员须每日检查车辆机油，刹车油、防冻液、轮胎气压等，如发现问题必须立即补充。所需费用由乙方承担。否则因此引起的一切后果由乙方负责。" , normal_desc_fontChinese);

            Paragraph paragraph31 = new Paragraph("9、严禁酒后驾驶租赁汽车，严禁使用租赁汽车进行违法犯罪活动，严禁使用租赁车辆参加竞赛及作测试使用，严禁使用租赁汽车进行营利活动，严禁使用租赁汽车运送易燃、易爆、易腐食物品。" , normal_desc_fontChinese);
            Paragraph paragraph32 = new Paragraph("10、乙方对租赁汽车上的特性标志不得私自摘除、涂改，亦不得私自拆装、附加他物于租赁汽车，否则发生损失由乙方负责。" , normal_desc_fontChinese);
            Paragraph paragraph33 = new Paragraph("11、乙方不得私自拆装租赁汽车车体（件），一经发现由乙方承担全部经济索赔，如乙方私自拆装里程表，造成租赁车辆无法计费，甲方除按正常计费外，每日加收500-1000公里超公里费用。" , normal_desc_fontChinese);
            Paragraph paragraph34 = new Paragraph("12、此租赁车辆的所有权属甲方，乙方不得将租赁汽车转借、转租、抵押、倒卖给他人，其行为无效；由此产生的一切后果由乙方承担。" , normal_desc_fontChinese);
            Paragraph paragraph35 = new Paragraph("13、乙方必须在合同规定的时间里归还租赁汽车，地址为：_________________________________超过规定的还车日期7天，乙方还未将车还到上述指定地点，甲方有权采取必要措施收回该车或上报有关司法部门按有关法律规定执行，由此产生的一切后果由承租方承担。乙方归还汽车时，必须要求甲方在本合同第一页还回日期上注明，需由甲方接车人签收后方为归还，否则视为乙方未归还车辆。\n" , normal_desc_fontChinese);
            Paragraph paragraph36 = new Paragraph("14、乙方还车时车况必须与“车况说明”中的车况登记相一致，并经甲方专业人员验收。验收时发现车辆有新的划痕、刮伤、碰撞、损坏、设备折损、证件丢失等现象时，乙方应按照实际损失缴纳车损费及其他相应的费用。" , normal_desc_fontChinese);
            Paragraph paragraph37 = new Paragraph("15、乙方超月使用租赁汽车的，须于每月底最后一日将租赁汽车运行公里如实报给甲方，以确保租赁汽车每5000公里进行一次级别保养。乙方必须按甲方的保养、检测要求及时将租赁汽车送到指定地点进行保养或者检测。因乙方原因造成租赁汽车脱保损失，由乙方负责赔偿。并按每脱保一次，赔偿400元，每超500公里，增加100元，逐次、逐公里累计由乙方承担经济责任。" , normal_desc_fontChinese);
            Paragraph paragraph38 = new Paragraph("16、乙方在租赁期间内，对租赁汽车必须妥善保管，停放地点安全可靠。甲方在其认为适当的时间，有权对租赁汽车的使用与保养、保管情况进检查，乙方须给予配合。因保管、停放不善造成的租赁汽车丢失及各种损失，由乙方承担全部经济损失赔偿。" , normal_desc_fontChinese);
            Paragraph paragraph39 = new Paragraph("七、其他规定" , normal_desc_fontChinese);
            Paragraph paragraph40 = new Paragraph("1、乙方注册名称、地址、通信电话发生变更，须事前及时通知甲方，违者视违约处理。" , normal_desc_fontChinese);
            Paragraph paragraph41 = new Paragraph("2、乙方使用租赁汽车的全部费用（含邮费、过路费、停车费等）由乙方自理。还车时，存油量低于提车时的存油，乙方应向甲方缴纳不足部分的燃料费用。" , normal_desc_fontChinese);
            Paragraph paragraph42 = new Paragraph("3、车辆在租赁期间发生违章罚款等费用由乙方承担。" , normal_desc_fontChinese);
            Paragraph paragraph43 = new Paragraph("4、乙方在租赁期间聘用甲方驾驶员使用租赁汽车发生行车事故的，其损失赔偿费用由甲、乙双方各承担50%。租赁期间乙方有聘用驾驶员或者非甲方配备驾驶员的主观故意责任，发生行车事故，都由乙方承担交通管理部门的罚款及其他惩罚性损失。" , normal_desc_fontChinese);
            Paragraph paragraph44 = new Paragraph("5、租赁期间内遇国家重大经济政策变化，或甲、乙方遇有不可抗拒的客观情况时，经甲、乙双方协商，可对本合同条款作相应的调整。" , normal_desc_fontChinese);
            Paragraph paragraph45 = new Paragraph("6、双方签订本合同后还须填写甲方的相应附件表格及车辆交接清单。所填写的表格及清单为本合同的组成部分，于本合同具有同等法律效力。" , normal_desc_fontChinese);
            Paragraph paragraph46 = new Paragraph("7、乙方如提出终止合同的：时间在一星期内的，原则上不退还租金，甲方放弃索赔权；时间在一个星期以上者，乙方补偿甲方原定而未出租租赁车辆汽车天数的50%租金。" , normal_desc_fontChinese);
            Paragraph paragraph47 = new Paragraph("8、乙方需延长租赁期的，应于租赁期满7天前通知甲方，并按照本合同的规定办理延期合同，未签订延期合同的，按照本合同第四条第一项规定收取超时费用。" , normal_desc_fontChinese);
            Paragraph paragraph48 = new Paragraph("9、乙方聘用甲方驾驶员的，须支付甲方驾驶员工资福利费用_____元/日，按天支付。甲方驾驶员每周工作40小时，每天为8小时。每超一小时，由乙方向甲方支付_____元加班费用，按月累计缴纳。其它按《劳动法》有关规定处理。" , normal_desc_fontChinese);
            Paragraph paragraph49 = new Paragraph("10、乙方必须保证租赁汽车每月___日到甲方指定的地点参加车辆例行保养和性能情况检查。逾期不参加检查的视乙方违约。" , normal_desc_fontChinese);
            Paragraph paragraph50 = new Paragraph("11、本合同未尽事宜或者需要更改的内容，由甲方与乙方商议补充或者修改。" , normal_desc_fontChinese);
            Paragraph paragraph51 = new Paragraph("八、违约责任" , normal_desc_fontChinese);
            Paragraph paragraph52 = new Paragraph("1、本合同不得撤回违反，违约方向守约方支付租金20%的违约金。赔偿对方因此造成的一切损失。" , normal_desc_fontChinese);
            Paragraph paragraph53 = new Paragraph("2、乙方在使用租赁汽车过程中违反本合同的规定，除承担前文违约责任外，甲方有权随时收回租赁车辆，终止本合同。本合同终止，乙方仍应承担违约的赔偿责任。" , normal_desc_fontChinese);
            Paragraph paragraph54 = new Paragraph("九、本合同的履行地为甲方住所地，甲方的车辆在___________________交付给乙方。\n" , normal_desc_fontChinese);
            Paragraph paragraph55 = new Paragraph("十、本合同自签订盖章时发生法律效力，至车辆归还，费用结清之日终止。但乙方在使用租赁汽车过程中发生的未了的经济责任情况，乙方不因本合同终止而免责，仍由乙方相对应后果承担责任。" , normal_desc_fontChinese);
            Paragraph paragraph56 = new Paragraph("十一、如履行本合同发生争议，双方约定由扬州市广陵区人民法院管辖。" , normal_desc_fontChinese);
            Paragraph paragraph57 = new Paragraph("十二、本合同一式两份，甲、乙双方各执一份。" , normal_desc_fontChinese);
            Paragraph paragraph58 = new Paragraph("出租方：扬州瑞特汽车服务有限公司    法定代表人：                 住所地：" , normal_desc_fontChinese);
            Paragraph paragraph59 = new Paragraph("承租方：                                           证件号（身份证）：                            联系电话：" , normal_desc_fontChinese);


            // 用 Document 对象、File 对象获得 PdfWriter 输出流对象
            PdfWriter.getInstance(pdfDoc, pdfFile);
            pdfDoc.open();  // 打开 Document 文档

            // 添加一个文字段落、一张图片
            pdfDoc.add(paragraph1);
            pdfDoc.add(new Chunk("\n\n"));
            pdfDoc.add(paragraph2);
            pdfDoc.add(paragraph3);
            pdfDoc.add(paragraph4);
            pdfDoc.add(paragraph5);
            pdfDoc.add(paragraph6);
            pdfDoc.add(paragraph7);
            pdfDoc.add(paragraph8);
            pdfDoc.add(paragraph9);
            pdfDoc.add(paragraph10);
            pdfDoc.add(paragraph11);
            pdfDoc.add(paragraph12);
            pdfDoc.add(paragraph13);
            pdfDoc.add(paragraph14);
            pdfDoc.add(image);


            pdfDoc.newPage();
            pdfDoc.add(paragraph15);
            pdfDoc.add(paragraph16);
            pdfDoc.add(paragraph17);
            pdfDoc.add(paragraph18);
            pdfDoc.add(paragraph19);
            pdfDoc.add(paragraph20);
            pdfDoc.add(paragraph21);
            pdfDoc.add(paragraph22);
            pdfDoc.add(paragraph23);
            pdfDoc.add(paragraph24);
            pdfDoc.add(paragraph25);
            pdfDoc.add(paragraph26);
            pdfDoc.add(paragraph27);
            pdfDoc.add(paragraph28);
            pdfDoc.add(paragraph29);
            pdfDoc.add(paragraph30);
            pdfDoc.add(paragraph31);
            pdfDoc.add(paragraph32);
            pdfDoc.add(paragraph33);
            pdfDoc.add(paragraph34);
            pdfDoc.add(paragraph35);
            pdfDoc.add(paragraph36);
            pdfDoc.add(paragraph37);
            pdfDoc.add(paragraph38);
            pdfDoc.add(paragraph39);
            pdfDoc.add(paragraph40);
            pdfDoc.add(paragraph41);
            pdfDoc.add(paragraph42);
            pdfDoc.add(paragraph43);
            pdfDoc.add(paragraph44);
            pdfDoc.add(paragraph45);
            pdfDoc.add(paragraph46);
            pdfDoc.add(paragraph47);
            pdfDoc.add(paragraph48);
            pdfDoc.add(paragraph49);
            pdfDoc.add(paragraph50);
            pdfDoc.add(paragraph51);
            pdfDoc.add(paragraph52);
            pdfDoc.add(paragraph53);
            pdfDoc.add(paragraph54);
            pdfDoc.add(paragraph55);
            pdfDoc.add(paragraph56);
            pdfDoc.add(paragraph57);
            pdfDoc.add(paragraph58);
            pdfDoc.add(paragraph59);

            pdfDoc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


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
    }
}