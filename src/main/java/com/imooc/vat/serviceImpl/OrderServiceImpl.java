package com.imooc.vat.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.vat.dao.OrdersMapper;
import com.imooc.vat.entity.Orders;
import com.imooc.vat.entity.OrdersExample;
import com.imooc.vat.entity.OrdersExample.Criteria;
import com.imooc.vat.service.OrderService;

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

}
