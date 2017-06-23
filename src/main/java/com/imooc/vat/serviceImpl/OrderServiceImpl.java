package com.imooc.vat.serviceImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.vat.dao.OrdersMapper;
import com.imooc.vat.entity.OrderExportResult;
import com.imooc.vat.entity.Orders;
import com.imooc.vat.entity.OrdersExample;
import com.imooc.vat.entity.OrdersExample.Criteria;
import com.imooc.vat.service.OrderService;
import com.imooc.vat.util.ExcelExporter;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrdersMapper orderMapper;
	@Override
	public List<Orders> getAllOrders() {
		List<Orders> list=new ArrayList<Orders>();
		OrdersExample example=new OrdersExample();
		Criteria criteria=example.createCriteria();
		criteria.andShipmentsNoIsNotNull();
		list=orderMapper.selectByExample(example);
		return list;
	}
	@Override
	public List<OrderExportResult> getExportList() throws IllegalAccessException, InvocationTargetException {
		List<Orders> list=new ArrayList<Orders>();
		OrdersExample example=new OrdersExample();
		Criteria criteria=example.createCriteria();
		criteria.andShipmentsNoIsNotNull();
		list=orderMapper.selectByExample(example);
		
		List<OrderExportResult> listEx=new ArrayList<>();
		for(Orders item:list){
			OrderExportResult result=new OrderExportResult();
			BeanUtils.copyProperties(result, item);
			listEx.add(result);
		}

		
		return listEx;
	}
	@Override
	public String ExportList(HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
		List<OrderExportResult> list=this.getExportList();
		String path="";
		//导出文件Excel
		ExcelExporter<OrderExportResult> exporter = new ExcelExporter<>();
		exporter.setTitle("InvoiceExcel");
		exporter.setEntityType(OrderExportResult.class);
		exporter.setEntities(list);
		try {
			path=exporter.exportExcel(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

}
