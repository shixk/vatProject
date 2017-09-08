package com.imooc.vat.service;

public interface Producer {
	
	public void sendMessage(String routeKey,Object data);
}
