package com.dgq.consumer.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws22.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dgq.consumer.service.MyWebService;

@Configuration
public class WebServiceConfig{
	
	@Autowired
	private Bus bus;
	
	@Autowired
	private MyWebService mywebservice;
	
	@Bean
    public ServletRegistrationBean<CXFServlet> wsDispatcherServlet(){
        return new ServletRegistrationBean<CXFServlet>(new CXFServlet(),"/service/*");//发布服务名称
    }
	
	@Bean
	public Endpoint endpoint(){
		Endpoint endpoint = new EndpointImpl(bus, mywebservice);
		endpoint.publish("/MyWebService");
		return endpoint;
	}

	
}