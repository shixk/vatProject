package com.imooc.vat.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.vat.entity.Orders;
import com.imooc.vat.service.Producer;

@Controller
@RequestMapping(value="mqtest")
public class MqTestController {
	
	@Autowired
	private Producer producer;
	
	@ResponseBody
	@RequestMapping(value="/send")
	public String sendMessage(HttpServletRequest request,String str){
		
		//producer.sendMessage("fooTestKey", str);
		Orders order=new Orders();
		order.setCustName("shixk");
		order.setCustName("18112345678");
		order.setShipmentsNo("N0005623");
		order.setPayTime(new Date());
		
		producer.sendMessage("fooTestKey", order);
		return "success";
	}

}
