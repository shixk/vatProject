package com.imooc.vat.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;


public class MessageConsumer implements MessageListener{

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}

}
