package com.imooc.vat.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/home")
public class HomeController {
	private static final Logger log = Logger.getLogger(HomeController.class);
	
	/**
	 * 到home.jsp页面
	 * @return
	 */
	@RequestMapping(value="/toHomeJsp")
	public ModelAndView toHistoryInvoiceQueryJsp(){
		log.info("user is logining home page!");
		return new ModelAndView("/home/index");
	}
}
