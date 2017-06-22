package com.imooc.vat.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {

	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	// 常用日期模式
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	private static final String DATE_FORMAT1 = "yyyy-MM-dd";

	/**
	 * 将String 转化为时间对象
	 * 
	 * @param str
	 *            String
	 * @param pattern
	 *            模式，i.e. yyyy-MM-dd HH:mm:ss
	 * @return Date
	 */
	public static Date string2date(String str, String pattern) {
		// yyyy-MM-dd HH:mm:ss
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		return date;
	}
	
	/**
	 * @Description: 此方法用于   将String 转化为时间对象
	 * @param str  时间      
	 * @param pattern    模式，i.e. yyyy-MM-dd HH:mm:ss
	 * @return
	 * @throws ParseException
	 * @date 2015年11月2日 下午10:45:27   
	 * @author fxc
	 */
	public static Date string2dateThrowEx(String str, String pattern) throws ParseException {
		// yyyy-MM-dd HH:mm:ss
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		date = sdf.parse(str);
		return date;
	}

	/**
	 * 格式化时间对象，输出String
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            模式，i.e. yyyy-MM-dd HH:mm:ss
	 * @return String
	 */
	public static String date2string(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 获取当前时间，以String 格式输出，yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String now() {
		Calendar cNow = Calendar.getInstance();
		Date dNow = cNow.getTime();
		return date2string(dNow, DATE_FORMAT);
	}

	/**
	 * 获取明天的时间，以String 格式输出，yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String tommorrow() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		Date t = c.getTime();
		return date2string(t, DATE_FORMAT);
	}

	/**
	 * 获取第二天的时间，以String 格式输出，yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String nextDay(String date) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT1); 
		try {   
		    Date d = format.parse(date);   
		    Calendar c = Calendar.getInstance();   
		    c.setTime(d);   
		    c.add(Calendar.DATE, 1);   
		    Date t = c.getTime();    
		    return date2string(t, DATE_FORMAT);
		} catch (ParseException e) {  
			logger.error(e.getMessage());
			return "";
		} 
	}
	
	/**
	 * 将时间格式字符串转为毫秒值
	 */
	public static String stringToSeconds(String time) {
		long times = string2date(time, DATE_FORMAT).getTime();
		return String.valueOf(times);
	}

}
