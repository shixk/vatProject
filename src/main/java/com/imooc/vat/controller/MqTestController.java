package com.imooc.vat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.vat.service.Producer;

@Controller
@RequestMapping(value="mqtest")
public class MqTestController {
	
	@Autowired
	private Producer producer;
	
	@ResponseBody
	@RequestMapping(value="/send")
	public String sendMessage(HttpServletRequest request,String str){
		
		producer.sendMessage("fooTestKey", str);
		return "success";
	}

}
