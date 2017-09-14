package com.imooc.vat.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * rabbitmq的消费者
 * @author shixk1
 *
 */
public class MessageConsumer implements MessageListener{

	private static final Logger log=LoggerFactory.getLogger(MessageConsumer.class);
	
	@Override
	public void onMessage(Message message) {
		log.info("开始接收mq的内容！");
		log.info(message.toString());
		//System.out.println(message);
	}

}
