package com.imooc.vat.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.imooc.vat.entity.OrderExportResult;
import com.imooc.vat.entity.Orders;

public interface OrderService {
	
	List<Orders> getAllOrders();
	
	List<OrderExportResult> getExportList() throws IllegalAccessException, InvocationTargetException;

	String ExportList(HttpServletRequest request) throws IllegalAccessException, InvocationTargetException;
}
