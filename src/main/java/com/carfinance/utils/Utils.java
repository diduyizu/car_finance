package com.carfinance.utils;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
	final Logger logger = LoggerFactory.getLogger(Utils.class);

	public static String encode(String source , String encoding , BitSet notEncoded) throws UnsupportedEncodingException {
		byte[] bytes = encode(source.getBytes(encoding) , notEncoded);
		return new String(bytes , "US-ASCII");
	}

	private static byte[] encode(byte[] source , BitSet notEncoded) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(source.length * 2);
		for (int i = 0 ; i < source.length ; i++) {
			int b = source[i];
			if (b < 0) {
				b += 256;
			}
			if (notEncoded.get(b)) {
				bos.write(b);
			} else {
				bos.write('%');
				char hex1 = Character.toUpperCase(Character.forDigit((b >> 4) & 0xF , 16));
				char hex2 = Character.toUpperCase(Character.forDigit(b & 0xF , 16));
				bos.write(hex1);
				bos.write(hex2);
			}
		}
		return bos.toByteArray();
	}

	public static Pattern p = Pattern.compile("[0-9-]+");

	public static String filte(String str) {
		if (str == null) str = "";
		return p.matcher(str).replaceAll("?").trim();
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	// 获取时间差方法
	/*
	 * 昨天 刚刚 1分钟内 分钟 1小时内 几小时 今天 日期
	 */
	public static String findTimeDiff(String time) {
		try {
			// Date nowDate=new Date();//不带时分秒的值
			Calendar c = Calendar.getInstance();// 带时分秒的值
			// Date dd=c.getTime();//带时分秒的值
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			long s = df1.parse(time).getTime();
			long e = df1.parse(df1.format(c.getTime())).getTime();
			// System.out.println(s+","+e);
			if (e == s) {// 今天
				SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				long timeLong = c.getTimeInMillis() - df2.parse(time).getTime();
				int b = (int) timeLong / 1000;
				if (b < 1) {
					return "刚刚";
				} else if (1 <= b && b < 60) {
					return b + "秒之前";
				} else if (b < 3600 && b >= 60) {
					// //精确到分钟
					return ((int) b / 60) + "分钟之前";
					// return ((int)b/60)+(b%60==0?"分钟之前":"分钟"+(b%60)+"秒之前");
				} else if (b < 3600 * 24 && b >= 3600) {
					// //精确到小时
					return ((int) b / 3600) + "小时之前";
					// return
					// ((int)b/3600)+(b%3600==0?"小时之前":"小时"+(int)((b%3600)/60)+"分钟之前");
				} else {
					return "未知时间";
				}
			} else if ((e - s) / (3600000 * 24) == 1) {// 昨天
				// SimpleDateFormat df2 = new
				// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// SimpleDateFormat df3 = new SimpleDateFormat("HH:mm:ss");
				// String hour=df3.format(df2.parse(time));
				// return "昨天"+hour;
				return "昨天 ";
			} else if ((e - s) / (3600000 * 24) == 2) {// 前天
				// SimpleDateFormat df2 = new
				// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// SimpleDateFormat df3 = new SimpleDateFormat("HH:mm:ss");
				// String hour=df3.format(df2.parse(time));
				// return "前天 "+hour;
				return "前天";
			} else {// 前天
				SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return df1.format(df2.parse(time));
				// return time;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return "未知时间";
		}
	}

	public static String nowTime(String format) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(new java.util.Date());
	}

	/**
	 * 
	 * 基本功能：判断标记是否存在
	 * <p>
	 * 
	 * @param input
	 * @return boolean
	 */
	public static boolean hasSpecialChars(String input) {
		boolean flag = false;
		if ((input != null) && (input.length() > 0)) {
			char c;
			for (int i = 0 ; i <= input.length() - 1 ; i++) {
				c = input.charAt(i);
				switch (c) {
					case '>':
						flag = true;
						break;
					case '<':
						flag = true;
						break;
					case '"':
						flag = true;
						break;
					case '&':
						flag = true;
						break;
				}
			}
		}
		return flag;
	}

	/**
	 * 
	 * 基本功能：替换标记以正常显示
	 * <p>
	 * 
	 * @param input
	 * @return String
	 */
	public static String replaceTag(String input) {
		if (!hasSpecialChars(input)) { return input; }
		StringBuffer filtered = new StringBuffer(input.length());
		char c;
		for (int i = 0 ; i <= input.length() - 1 ; i++) {
			c = input.charAt(i);
			switch (c) {
				case '<':
					filtered.append("&lt;");
					break;
				case '>':
					filtered.append("&gt;");
					break;
				case '"':
					filtered.append("&quot;");
					break;
				case '&':
					filtered.append("&amp;");
					break;
				default:
					filtered.append(c);
			}

		}
		return (filtered.toString());
	}

	/**
	 * 得到本月的第一天
	 * 
	 * @return
	 */
	public static String getMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH , calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

		return dateFormat("yyyy-MM-dd" , calendar.getTime());
	}

	/**
	 * 得到本月的最后一天
	 * 
	 * @return
	 */
	public static String getMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH , calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return dateFormat("yyyy-MM-dd" , calendar.getTime());
	}

	/**
	 * 得到上月的第一天
	 * 
	 * @return
	 */
	public static String getLastMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH , calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.add(Calendar.MONTH , -1);
		return dateFormat("yyyy-MM-dd" , calendar.getTime());
	}

	/**
	 * 得到上月的最后一天
	 * 
	 * @return
	 */
	public static String getLastMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH , calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.add(Calendar.MONTH , -1);
		return dateFormat("yyyy-MM-dd" , calendar.getTime());
	}

	public static String dateFormat(String format , Date date) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(date);
	}

	public static Random _random = new Random();

	/**
	 * 生成随机数 param length 随机数长度，长度不能大于10
	 * 
	 * @return
	 */
	public static String randomNum(int length) {
		int x = _random.nextInt(999999);
		String wholeNum = ("000000" + x);
		return wholeNum.substring(wholeNum.length() - 6 , wholeNum.length());
	}

	/**
	 * 将中间4位号码转*
	 * 
	 * @param dn
	 * @return
	 */
	public static String formatDn(String dn) {
		return dn.substring(0 , 3) + "****" + dn.substring(7 , dn.length());
	}

	/**
	 * 获取字符串的长度，如果有中文，则每个中文字符计为2位
	 * 
	 * @param value
	 *            指定的字符串
	 * @return 字符串的长度
	 */
	public static int getStrlength(String value) {
		int valueLength = 0;
		String chinese = "[\u0391-\uFFE5]";
		/* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
		for (int i = 0 ; i < value.length() ; i++) {
			/* 获取一个字符 */
			String temp = value.substring(i , i + 1);
			/* 判断是否为中文字符 */
			if (temp.matches(chinese)) {
				/* 中文字符长度为2 */
				valueLength += 2;
			} else {
				/* 其他字符长度为1 */
				valueLength += 1;
			}
		}
		return valueLength;
	}

	/*
	 * 按字节长度截取字符串
	 * 
	 * @param str 将要截取的字符串参数
	 * 
	 * @param toCount 截取的字节长度
	 * 
	 * @param more 字符串末尾补上的字符串
	 * 
	 * @return 返回截取后的字符串
	 */
	public static String substring(String str , int toCount , String more) {
		if (StringUtils.isBlank(str)) {// 若字符串为空，直接返回
			return "";
		}
		if (str.trim().getBytes().length <= toCount) {// 截取的字节长度大于字符串字节长度，直接返回
			return str;
		}
		int loop = 0;
		StringBuffer stringBuffer = new StringBuffer();
		char[] tempChar = str.toCharArray();
		for (int i = 0 ; i < tempChar.length && toCount > loop ; i++) {// 根据字符串字节获取截取后字符串
			String tmp = str.valueOf(tempChar[i]);
			byte[] b = tmp.getBytes();
			loop += b.length;
			stringBuffer.append(tempChar[i]);//
		}
		if (toCount == loop || (toCount == loop - 1)) {// 添加末尾字符串
			stringBuffer.append(more);
		}
		return stringBuffer.toString();
	}

	// 模糊查询时特殊字符的转义
	public static String escapeForSpecialChar(String content) {
		if (StringUtils.isBlank(content)) {// 为空，返回空
			return "";
		}
		content = content.replace("\\" , "\\\\");// 转义前的字符串不为空，执行替换操作
		content = content.replace("%" , "\\%");
		content = content.replace("_" , "\\_");
		return content;
	}

	/*
	 * 昨天 刚刚 1分钟内 分钟 1小时内 几小时 今天 日期
	 */
	public static String changeTimeDiff(String time) {
		try {
			Calendar c = Calendar.getInstance();// 带时分秒的值
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			long s = df1.parse(time).getTime();
			long e = df1.parse(df1.format(c.getTime())).getTime();
			if (e == s) {// 今天
				SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				long timeLong = c.getTimeInMillis() - df2.parse(time).getTime();
				int b = (int) timeLong / 1000;
				if (b < 1) {
					return "刚刚";
				} else if (1 <= b && b < 60) {
					return b + "秒之前";
				} else if (b < 3600 && b >= 60) {
					// //精确到分钟
					return ((int) b / 60) + "分钟之前";
				} else if (b < 3600 * 24 && b >= 3600) {
					// //精确到小时
					return ((int) b / 3600) + "小时之前";
				} else {
					return "未知时间";
				}
			} else if ((e - s) / (3600000 * 24) == 1) {// 昨天
				return "昨天 ";
			} else if ((e - s) / (3600000 * 24) == 2) {// 前天
				return "前天";
			} else {// 前天
				SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return df1.format(df2.parse(time));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return "未知时间";
		}
	}
}
