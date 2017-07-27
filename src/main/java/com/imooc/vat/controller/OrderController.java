package com.imooc.vat.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.imooc.vat.entity.OrderExportResult;
import com.imooc.vat.entity.Orders;
import com.imooc.vat.service.OrderService;

@Controller
@RequestMapping(value="/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	RedisTemplate redisTemplate;
	
	@RequestMapping(value="/index")
	public ModelAndView toOrderList(HttpServletRequest request){
		
		
		return new ModelAndView("/order/orderlist");
	}
	
	
	@ResponseBody
	@RequestMapping(value="/list")
	public Map getOrderList(HttpServletRequest request){
		Map map=new HashMap();
		
		List<Orders> list=orderService.getAllOrders();
		Orders order=list.get(0);
		map.put("order", order);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/exportExcel")
	public Map exportExcel(HttpServletRequest request,String orderNo){
		Map map=new HashMap();

		try {
			String file=orderService.ExportList(request);
			map.put("success", true);
			map.put("filename", file);
			map.put("Msg", "导出成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("Msg", "导出失败"+e.getMessage());
		} 
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/setCache")
	public String setCache(String val){
		redisTemplate.opsForValue().set("test", val);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/getVal")
	public String getCache(String key){
		
		String val=(String)redisTemplate.opsForValue().get(key);
		
		return val;
	}
}
