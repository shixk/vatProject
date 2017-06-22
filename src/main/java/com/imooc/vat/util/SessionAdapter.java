package com.imooc.vat.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Session工具类
 * @author wuqiang3
 *
 */
public class SessionAdapter {
	
	/**
	 * 已经登录的用用户
	 */
	public final static String LOGINEDUSER = "loginedUser"; //已经登录的用用户
	
	/**
	 * 将对象保持到Session中
	 * @param request
	 * @param key
	 * @param value
	 */
	public static void setAttribute(HttpServletRequest request,String key,Object value) {
		if(request.getSession().getAttribute(key) != null){
			request.getSession().removeAttribute(key);
		}
		request.getSession().setAttribute(key,value);
	}
	
	/**
	 * 在Session中获取key对应的数据
	 * @param request
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static Object getAttribute(HttpServletRequest request,String key) {
		return request.getSession().getAttribute(key);
	}
	
	/**
	 * 在Session中移除某个Key
	 * @param request
	 * @param key
	 */
	public static void removeAttribute(HttpServletRequest request,String key) {
		request.getSession().removeAttribute(key);
	}
	
	
}
