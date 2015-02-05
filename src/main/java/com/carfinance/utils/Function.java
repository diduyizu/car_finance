package com.carfinance.utils;

//import com.linkage.educloud.module.pcenter.pager.domain.ProblemType;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;


public class Function {

    /**
     * 转化字节方法
     *
     * @param b
     * @return 32位
     */
    private static String byte2hex(byte[] b) {
        String hs = "";
        String stmp;
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else hs = hs + stmp;
            //if (n < b.length - 1) hs = hs + "";
        }
        return hs.toUpperCase();
    }

    /**
     * md5加密
     *
     * @param str
     * @return 加密后的32位
     */
    public static String md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            return byte2hex(digest);
        }
        catch (java.security.NoSuchAlgorithmException ex) {
        }
        return null;
    }

    /**
     * 判断一个String是否为空
     *
     * @param str
     * @return String
     */
    public static String notNull(String str) {
        if (str == null) {
            return "";
        }
        else {
            return str.trim();
        }
    }

    /**
     * 判断一个String是否为空
     *
     * @param str
     * @return String
     */
    public static String notNull(String str, String defStr) {
        if (str == null) {
            return defStr;
        }
        else if (str.trim().equals("")) {
            return defStr;
        }
        else {
            return str.trim();
        }
    }

    /**
     * 处理页面显示为空的数据
     *
     * @param str
     * @return String
     */
    public static String ifNull(String str) {
        if (str == null) {
            return "&nbsp;";
        }
        else if (str.trim().equals("")) {
            return "&nbsp;";
        }
        else {
            return str.trim();
        }
    }

    /**
     * 将String转换为整形
     *
     * @param inputNumber
     * @return int
     */
    public static int parseInt(String inputNumber) {
        if (inputNumber == null)
            return -1;
        try {
            return Integer.parseInt(inputNumber.trim());
        }
        catch (Exception ex) {
            return -1;
        }
    }

    /**
     * 将String转换为char
     *
     * @param inputNumber
     * @return int
     */
    public static char parseChar(String inputNumber) {
        if (inputNumber == null)
            return ' ';
        try {
            return inputNumber.trim().toCharArray()[0];
        }
        catch (Exception ex) {
            return ' ';
        }
    }

    /**
     * 将String转换为整形
     *
     * @param inputNumber
     * @param defaultNum
     * @return int
     */
    public static int parseInt(String inputNumber, int defaultNum) {
        if (inputNumber == null)
            return defaultNum;
        try {
            return Integer.parseInt(inputNumber.trim());
        }
        catch (Exception ex) {
            return defaultNum;
        }
    }

    /**
     * 将String转换为Double
     */
    public static double parseDouble(String value, double defaultNum) {
        if (value == null || "".equals(value)) {
            return defaultNum;
        }
        try {
            return Double.parseDouble(value);
        }
        catch (Exception ex) {
            return defaultNum;
        }
    }

    /**
     * 将String转换为Long
     */
    public static long parseLong(String value, long defaultNum) {
        if (value == null || "".equals(value)) {
            return defaultNum;
        }
        try {
            return Long.parseLong(value);
        }
        catch (Exception ex) {
            return defaultNum;
        }
    }

    public static String subString(String value, int length){
        if(value == null || value.equals("")){
            return "";
        }
        if(value.length() <= length){
            return value;
        }
        else{
            return value.substring(0, length) + "...";
        }
    }
    
    /**
     * 对List<object> 做去重复
     */
    @SuppressWarnings("unchecked")
	public static List<String> checkList(List<String> alList){
    	Iterator it1 = alList.iterator();  
    	Hashtable ht = new Hashtable();  
    	while(it1.hasNext()){   
    		Object obj = it1.next();   
    		ht.put(obj, obj);  
    	}    
    	Iterator it2 = ht.keySet().iterator();  
    	List list = new ArrayList(); 
    	while(it2.hasNext()){   
    		list.add(it2.next().toString());  
    	}
    	return list;
    }


    // 数字转换成中文 getChinese(2147483648l,0) 数字+l,0
    public static String getChinese(long number, int depth) {
        if (depth < 0)
            depth = 0;
        String chinese = "";
        String src = number + "";
        if (src.charAt(src.length() - 1) == 'l'
                || src.charAt(src.length() - 1) == 'L') {
            src = src.substring(0, src.length() - 1);
        }

        if (src.length() > 4)
            chinese = getChinese(Integer.parseInt(src.substring(0,
                    src.length() - 4)), depth + 1)
                    + getChinese(Integer.parseInt(src.substring(
                    src.length() - 4, src.length())), depth - 1);
        else {
            char prv = 0;
            for (int i = 0; i < src.length(); i++) {
                switch (src.charAt(i)) {
                    case '0':
                        if (prv != '0')
                            chinese = chinese + "零";
                        break;
                    case '1':
                        chinese = chinese + "一";
                        break;
                    case '2':
                        chinese = chinese + "二";
                        break;
                    case '3':
                        chinese = chinese + "三";
                        break;
                    case '4':
                        chinese = chinese + "四";
                        break;
                    case '5':
                        chinese = chinese + "五";
                        break;
                    case '6':
                        chinese = chinese + "六";
                        break;
                    case '7':
                        chinese = chinese + "七";
                        break;
                    case '8':
                        chinese = chinese + "八";
                        break;
                    case '9':
                        chinese = chinese + "九";
                        break;
                }
                prv = src.charAt(i);

                switch (src.length() - 1 - i) {
                    case 1:// 十
                        if (prv != '0')
                            chinese = chinese + "十";
                        break;
                    case 2:// 百
                        if (prv != '0')
                            chinese = chinese + "百";
                        break;
                    case 3:// 千
                        if (prv != '0')
                            chinese = chinese + "千";
                        break;

                }
            }
        }
        while (chinese.length() > 0
                && chinese.lastIndexOf("零") == chinese.length() - 1)
            chinese = chinese.substring(0, chinese.length() - 1);
        if (depth == 1)
            chinese += "万";
        if (depth == 2)
            chinese += "亿";

        return chinese;
    }
    public static void main(String args[]) {

        System.out.println(getChinese(2147483648l,0));

    }
}
