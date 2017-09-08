package com.imooc.vat.serviceImpl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.vat.service.Producer;

@Service
public class ProducerImpl implements Producer {

	@Autowired(required=false)
	private RabbitTemplate rabbitTemplate;
	
	@Override
	public void sendMessage(String routeKey, Object data) {

		rabbitTemplate.convertAndSend(routeKey, data);
	}

}
