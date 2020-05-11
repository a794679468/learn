package com.dgq.consumer.webservice;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
//import org.junit.Test;

import com.dgq.consumer.service.MyWebService;

public class CxfWebService{
	
	//动态调用
//	@Test
	public void test(){
		
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client =  dcf.createClient("http://localhost:8080/service/MyWebService?wsdl");
		
		try {
			Object[] objects = client.invoke("cxf", "我就是666");
			System.err.println("返回数据结果为：" + objects[0].toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//调用方式二，通过接口协议获取数据类型
	public void test1(){
		JaxWsProxyFactoryBean pfb =new JaxWsProxyFactoryBean();
		pfb.setAddress("http://localhost:8080/service/MyWebService?wsdl");
		pfb.setServiceClass(MyWebService.class);
		
		MyWebService mywebservice = (MyWebService) pfb.create();
		String result = mywebservice.cxf("你是杀币");
		System.out.println(result);
	}
	
	//调用方式三，通过接口协议获取数据类型,设置链接超时和响应时间
	public void test2(){
		JaxWsProxyFactoryBean pfb =new JaxWsProxyFactoryBean();
		pfb.setAddress("http://localhost:8080/service/MyWebService?wsdl");
		pfb.setServiceClass(MyWebService.class);
		
		MyWebService mywebservice = (MyWebService) pfb.create();
		String result = mywebservice.cxf("你是杀币");
		Client proxy = ClientProxy.getClient(mywebservice);
		HTTPConduit condit = (HTTPConduit) proxy.getConduit();
		HTTPClientPolicy policy = new HTTPClientPolicy();
		policy.setConnectionTimeout(1000);
		policy.setReceiveTimeout(1000);
		
		
	}
	
}