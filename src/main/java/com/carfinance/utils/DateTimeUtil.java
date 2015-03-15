/**
 * 文件名：DateTimeUtil.java
 *
 * 版本信息:1.0
 * 日期:2009-2-21
 * Copyright 联创科技(南京)有限公司 2009 版权所有
 *
 */
package com.carfinance.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Logger;

/**
 * 
 * 类描述:实现对时间相关格式转换
 * 修改备注:
 * @version 1.0
 *
 */
public class DateTimeUtil { // 服务器的时间
	private Logger m_logger = Logger.getLogger(this.getClass());

	private Calendar calendar = null;

	/**
	 * 构造函数，初始化时间发生器（无参数：当前时间）
	 */
	public DateTimeUtil() {
		calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
	}

	/**
	 * 构造函数：初始化时间发生器（带参数: 长整型时间数据：秒）
	 */
	public DateTimeUtil(long serverDateTime) {
		calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));

		calendar.setTimeInMillis(serverDateTime); // 指定时间
	}

	/**
	 * 构造函数：初始化时间发生器（带参数: 长整型时间数据）
	 */
	public DateTimeUtil(Date DateTime) {
		calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));

		calendar.setTimeInMillis(DateTime.getTime()); // 指定时间
	}

	/**
	 * 构造函数：初始化时间发生器（带参数:Calendar类实例）
	 */
	public DateTimeUtil(Calendar cal) {
		calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
		calendar = cal;
	}

	/**
	 * 构造函数：初始化时间发生器（带参数:DateTime） 参数格式：yyyy-MM-dd HH:mm:ss or yyyy-MM-dd
	 */
	public DateTimeUtil(String DateTime) {
		calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));

		SimpleDateFormat s = null;
		Date date = null;
		try {

			if (DateTime.length() > 12) {
				s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			} else {
				s = new SimpleDateFormat("yyyy-MM-dd");
			}

			date = new Date();

			try {
				date = s.parse(DateTime);
			} catch (ParseException e) {
				System.out.println("e:" + e.getMessage());
			}
		} catch (Exception ee) {
			System.out.println("ee:" + ee.getMessage());
		}
		calendar.setTime(date);
	}

	/**
	 * 构造函数：初始化时间发生器（带参数:DateTime） 参数格式：yyyy-MM-dd HH:mm:ss or yyyy-MM-dd
	 */
	public DateTimeUtil(String DateTime, String DateFormat) {
		calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));

		SimpleDateFormat s = null;
		Date date = null;

		try {
			s = new SimpleDateFormat(DateFormat);
			date = new Date();

			try {
				date = s.parse(DateTime);
			} catch (ParseException e) {
				System.out.println("e:" + e.getMessage());
			}
		} catch (Exception ee) {
			System.out.println("ee:" + ee.getMessage());
		}
		calendar.setTime(date);
	}

	/**
	 * 取日期-时间 yyyy-mm-dd HH:mm:ss
	 * 
	 * @return String
	 */
	public String getLongDate() {
		return this.getDate() + " " + this.getTime();
	}

	/**
	 * 取日期-时间 yyyymmddHHmmss
	 * 
	 * @return String
	 */
	public String getLongDateChar() {
		return this.getShortDate() + this.getShortTime();
	}

	/**
	 * 取时间型日期
	 * 
	 * @return Date
	 */
	public Date getDateTime() {
		return calendar.getTime();
	}

	/**
	 * 取日期 yyyy-mm-dd
	 * 
	 * @return String
	 */
	public String getDate() {
		String months, days, years;
		years = String.valueOf(this.getYear());

		if (this.getMonth() < 10) {
			months = "0" + this.getMonth();
		} else {
			months = String.valueOf(this.getMonth());
		}

		if (this.getDay() < 10) {
			days = "0" + this.getDay();
		} else {
			days = String.valueOf(this.getDay());
		}

		return years + "-" + months + "-" + days;
	}
	/**
	 * 取日期 yyyy-m-d
	 * add by benyp
	 * @return String
	 */
	public String getDate2() {
		String months, days, years;
		years = String.valueOf(this.getYear());

		
			months = String.valueOf(this.getMonth());
		
			days = String.valueOf(this.getDay());
	
		return years + "-" + months + "-" + days;
	}

	/**
	 * 取日期 yyyymmdd
	 * 
	 * @return String
	 */
	public String getShortDate() {
		String months, days, years;
		years = String.valueOf(this.getYear());

		if (this.getMonth() < 10) {
			months = "0" + this.getMonth();
		} else {
			months = String.valueOf(this.getMonth());
		}

		if (this.getDay() < 10) {
			days = "0" + this.getDay();
		} else {
			days = String.valueOf(this.getDay());
		}

		return years + "" + months + "" + days;
	}

	/**
	 * 取时间 hh:mm:ss
	 * 
	 * @return String
	 */
	public String getTime() {
		String hours, minutes, seconds;
		if (this.getHour() < 10) {
			hours = "0" + this.getHour();
		} else {
			hours = String.valueOf(this.getHour());
		}

		if (this.getMinute() < 10) {
			minutes = "0" + this.getMinute();
		} else {
			minutes = String.valueOf(this.getMinute());
		}

		if (this.getSecond() < 10) {
			seconds = "0" + this.getSecond();
		} else {
			seconds = String.valueOf(this.getSecond());
		}

		return hours + ":" + minutes + ":" + seconds;
	}

	/**
	 * 取时间 hhmmss
	 * 
	 * @return String
	 */
	public String getShortTime() {
		String hours, minutes, seconds;
		if (this.getHour() < 10) {
			hours = "0" + this.getHour();
		} else {
			hours = String.valueOf(this.getHour());
		}

		if (this.getMinute() < 10) {
			minutes = "0" + this.getMinute();
		} else {
			minutes = String.valueOf(this.getMinute());
		}

		if (this.getSecond() < 10) {
			seconds = "0" + this.getSecond();
		} else {
			seconds = String.valueOf(this.getSecond());
		}

		return hours + minutes + seconds;
	}

	/**
	 * 取年 yyyy
	 * 
	 * @return int
	 */
	public int getYear() {
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 取月 mm
	 * 
	 * @return int
	 */
	public int getMonth() {
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 取日 dd
	 * 
	 * @return int
	 */
	public int getDay() {
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 取小时 hh
	 * 
	 * @return int
	 */
	public int getHour() {
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 取分钟 mm
	 * 
	 * @return int
	 */
	public int getMinute() {
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 取秒钟 ss
	 * 
	 * @return int
	 */
	public int getSecond() {
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 取从1970年1月1日以来的时间（秒）
	 * 
	 * @return long
	 */
	public long getLongTime() {
		return calendar.getTimeInMillis() / 1000;
	}

	/**
	 * 取从当前日期的偏移日期:年份
	 * 
	 * @param years
	 * @return int
	 */
	public int getNextYear(int years) {
		calendar.add(Calendar.YEAR, years);

		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 取从当前日期的偏移日期:月份
	 * 
	 * @param months
	 * @return int
	 */
	public int getNextMonth(int months) {
		calendar.add(Calendar.MONTH, months);

		return calendar.get(Calendar.MONTH) + 1;
	}
	/**
	 * 返回选定日期的周
	 * @param week
	 * @return
	 */
	public int getNextWeek(int week)
	{
		calendar.add(Calendar.WEEK_OF_YEAR, week);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
	/**
	 * 取从当前日期的偏移日期:日期
	 * 
	 * @param dates
	 * @return int Calendar.DAY_OF_MONTH
	 */
	public int getNextDate(int dates) {
		calendar.add(Calendar.DATE, dates);

		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 取从当前日期偏移到的日期:具体时间值字符串
	 * 
	 * @param dateType
	 * @param timeNumber
	 * @return getLongDate()
	 */
	public String getNextDateTime(String dateType, int timeNumber) {
		if (dateType.equals("year")) {
			calendar.add(Calendar.YEAR, timeNumber);
		} else if (dateType.equals("month")) {
			calendar.add(Calendar.MONTH, timeNumber);
		} else if (dateType.equals("day")) {
			calendar.add(Calendar.DATE, timeNumber);
		} else if (dateType.equals("hour")) {
			calendar.add(Calendar.HOUR, timeNumber);
		} else if (dateType.equals("minute")) {
			calendar.add(Calendar.MINUTE, timeNumber);
		} else if (dateType.equals("second")) {
			calendar.add(Calendar.SECOND, timeNumber);
		}

		return this.getLongDate();
	}

	/**
	 * 取从当前日期偏移到的日期:具体时间值字符串 add by benyp(5260)：返回的时间不含0 如:2008-3-1 10:1:1
	 * 
	 * @param dateType
	 * @param timeNumber
	 * @return getLongDate()
	 */
	public String getNextDateTimeWithoutZero(String dateType, int timeNumber)
	{
		if (dateType.equals("year"))
			{
				calendar.add(Calendar.YEAR, timeNumber);
			}
		else
			if (dateType.equals("month"))
				{
					calendar.add(Calendar.MONTH, timeNumber);
				}
			else
				if (dateType.equals("day"))
					{
						calendar.add(Calendar.DATE, timeNumber);
					}
				else
					if (dateType.equals("hour"))
						{
							calendar.add(Calendar.HOUR, timeNumber);
						}
					else
						if (dateType.equals("minute"))
							{
								calendar.add(Calendar.MINUTE, timeNumber);
							}
						else
							if (dateType.equals("second"))
								{
									calendar.add(Calendar.SECOND, timeNumber);
								}
		return (this.getDate2() + " " + this.getHour() + ":" + this.getMinute() + ":" + this
				.getSecond());
	}
	
	/**
	 * 取从当前日期偏移到的日期:具体时间值字符串
	 * 
	 * @param dateType
	 * @param timeNumber
	 * @return getDate
	 */
	public String getNextDate(String dateType, int timeNumber)
	{
		if (dateType.equals("year"))
			{
				calendar.add(Calendar.YEAR, timeNumber);
			}
		else
			if (dateType.equals("month"))
				{
					calendar.add(Calendar.MONTH, timeNumber);
				}
			else
				if (dateType.equals("day"))
					{
						calendar.add(Calendar.DATE, timeNumber);
					}
				else
					if (dateType.equals("hour"))
						{
							calendar.add(Calendar.HOUR, timeNumber);
						}
					else
						if (dateType.equals("minute"))
							{
								calendar.add(Calendar.MINUTE, timeNumber);
							}
						else
							if (dateType.equals("second"))
								{
									calendar.add(Calendar.SECOND, timeNumber);
								}
		return this.getDate();
	}
	
	/**
	 * 获得特定日期是当年的第几天
	 * 
	 * @return int
	 */
	public int getNoDayOfYear() {
		return calendar.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 计算两个日期相隔的天数
	 * 
	 * @param firstDate
	 * @param secondDate
	 * @return int
	 */
	public static int nDaysBetweenTwoDate(Date firstDate, Date secondDate) {
		int nDay = (int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));
		return nDay;
	}

	/**
	 * 计算两个日期相隔的天数
	 * 
	 * @param firstString
	 * @param secondString
	 * @return int
	 */
	public static int nDaysBetweenTwoDate(String firstString, String secondString) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date firstDate = null;
		Date secondDate = null;

		try {
			firstDate = df.parse(firstString);
			secondDate = df.parse(secondString);
		} catch (Exception e) {
			// 日期型字符串格式错误
		}

		int nDay = (int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));

		return nDay;
	}

	/**
	 * 计算两个日期相隔的天数
	 * 
	 * @param firstString
	 * @param secondString
	 * @return int
	 */
	public static int nWeeksBetweenTwoDate(String firstString, String secondString) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date firstDate = null;
		Date secondDate = null;

		try {
			firstDate = df.parse(firstString);
			secondDate = df.parse(secondString);
		} catch (Exception e) {
			// 日期型字符串格式错误
		}

		int nWeek = (int) ((secondDate.getTime() - firstDate.getTime()) / (7 * 24 * 60 * 60 * 1000));

		return nWeek;
	}

	/**
	 * 返回特定日期处于一年中的第几周
	 * 周的定义：星期一到星期日
	 * 
	 * @return int
	 */
	public int getNoWeekOfYear() {
		calendar.add(Calendar.DATE, -1);
		int weeks = calendar.get(Calendar.WEEK_OF_YEAR);
		calendar.add(Calendar.DATE, 1);

		return weeks;
	}
	
	
	/**
	 * 返回特定日期处于一年中的第几周
	 * 周的定义：星期日到星期六
	 * @return
	 */
	public int getWeekOfYear()
	{
		int weeks = calendar.get(Calendar.WEEK_OF_YEAR);
		return weeks;
	}

	/**
	 * 返回特定日期处于一周中的第几天<br>
	 * 中国周一为一周第一天：CN<br>
	 * 国外周日为一周第一天：US
	 * 
	 * @return int
	 */
	public int getNoDayOfWeek(String country) {
		return calendar.get(Calendar.DAY_OF_WEEK)
				- Integer.parseInt(country.equals("CN") ? "1" : "0");
	}
	/**
	 * 东方是周一为一周的第一天：CN<br>
	 * 西方是周日为一周的第一天：US<br>
	 * 返回特定日期所处这一周的周一所处的日期
	 * 
	 * @return String
	 */
	public String getFirstDayOfWeek(String country) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		// 西方是周日为一周的第一天
		// 东方是周一为一周的第一天
		Date dateBegin = new Date();
		dateBegin.setTime(calendar.getTimeInMillis()
				- (long) (getNoDayOfWeek(country) - 1) * 24 * 60 * 60 * 1000);

		return formatter.format(dateBegin);
	}

	/**
	 * 东方是周一为一周的第一天：CN<br>
	 * 西方是周日为一周的第一天：US<br>
	 * 返回特定日期所处这一周的周末所处的日期
	 * 
	 * @return String
	 */
	public String getLastDayOfWeek(String country) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		// 西方是周日为一周的第一天
		// 东方是周一为一周的第一天
		Date dateEnd = new Date();
		dateEnd.setTime(calendar.getTimeInMillis()
				+ (long) (7 - getNoDayOfWeek(country)) * 24 * 60 * 60 * 1000);

		return formatter.format(dateEnd);
	}

	/**
	 * 获得特定日期是当年的第几天 返回：int
	 */
	public int getNOdays() {
		return calendar.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获得指定日期所在月的最后一天的日期
	 * 
	 * @return String
	 */
	public String getLastDayOfMonth() {
		// 移动到下一个月的第一天然后再后退一天
		// this.getNextMonth(1);
		// System.out.println(this.getYear()+";"+this.getMonth());
		calendar.set(this.getYear(), this.getMonth(), 1);
		this.getNextDate(-1);
		// Date dateEnd = new
		// Date(calendar.get(calendar.YEAR)+"-"+calendar.get(calendar.MONTH)+"-01");

		// return
		// this.getYear()+"-"+this.getMonth()+"-"+this.calendar.get(calendar.DAY_OF_MONTH);
		return this.getDate();
	}

	public static void main(String[] args) {
		Logger m_logger = Logger.getLogger(DateTimeUtil.class);
		DateTimeUtil timeUtil = new DateTimeUtil("2006-1-1");
		m_logger.info(String.valueOf(timeUtil.getNoWeekOfYear()));
	}
}