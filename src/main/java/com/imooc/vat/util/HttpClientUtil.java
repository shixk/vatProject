package com.imooc.vat.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;


/**
 * @ClassName: HttpClientUtil
 * @Description: HttpClient 调用跨域调用接口
 * @author yuanzhengda
 * @date 2015年10月12日 上午10:49:41
 */
public class HttpClientUtil {

	/**
	 * @Description:发送httpClint请求
	 * @param @param postData 请求路径
	 * @param @param map 请求数据
	 * @param @return
	 * @author yuanzhengda
	 * @throws IOException
	 * @throws HttpException
	 */
	public static String ReqHttp(String postData, Map<String, String> map)
			throws Exception {
		// 创建httpClint请求
		HttpClient client = new HttpClient();
		// POST格式请求方法
		PostMethod post = new PostMethod(postData); // 请求路径
		
		client.getParams().setContentCharset("UTF-8");
		// map 参数
		Iterator it = map.entrySet().iterator(); // 获取循环数据
		// 循环数据
		NameValuePair para1 = null;
		NameValuePair para2 = null;
		NameValuePair para3 = null;
		NameValuePair para4 = null;
		List<NameValuePair>  list = new ArrayList<NameValuePair>();
		while (it.hasNext()) {
			Entry entry = (Entry) it.next(); // 获取当前的数据
			String key = (String) entry.getKey();// 返回与此项对应的键
			String val = (String) entry.getValue();// 返回与此项对应的值
			para1 = new NameValuePair(key, val); // 设置参数值
			list.add(para1);
		}
		NameValuePair[] nvArr=null;
		if(list.size()>0){
			nvArr=new NameValuePair[list.size()];
			for(int i=0;i<list.size();i++){
				nvArr[i]=list.get(i);
			}
		}
		post.setRequestBody( nvArr); //绑定参数
		client.executeMethod(post); //
		String array = post.getResponseBodyAsString(); // 获取返回的字符串
		post.releaseConnection(); // 释放请求数据
		return array; // 返回String数据
	}
}
