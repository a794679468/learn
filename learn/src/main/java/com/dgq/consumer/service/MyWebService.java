package com.dgq.consumer.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace="http://service.consumer.dgq.com")
public interface MyWebService{
	
	@WebMethod
	public String cxf(String name);
}