package com.dgq.consumer.service.impl;



import javax.jws.WebService;

import org.springframework.stereotype.Component;

import com.dgq.consumer.service.MyWebService;

@WebService(serviceName="MyWebService",targetNamespace="http://service.consumer.dgq.com",endpointInterface="com.dgq.consumer.service.MyWebService")
@Component
public class MyWebServiceImpl implements MyWebService{

	@Override
	public String cxf(String name) {
		return name;
	}
	
	
}