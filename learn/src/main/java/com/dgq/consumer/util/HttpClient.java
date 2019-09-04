package com.dgq.consumer.util;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HttpClient{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static String httpPostUrlForPdf(String url) {
	    // 设置HTTP请求参数
		String result = null;
	    CloseableHttpClient client = HttpClients.createDefault();
	    HttpGet httpGet = new HttpGet(url);
	    try {
	        CloseableHttpResponse response = client.execute(httpGet);
	        result = EntityUtils.toString(response.getEntity(), "UTF-8");
	    } catch (ClientProtocolException e) {
//	        logger.error("http接口调用异常：url is::" + url, e);
	        return null;
	    } catch (IOException e) {
//	        logger.error("http接口调用异常：url is::" + url, e);
	        return null;
	    } finally {
	        try {
	            client.close();
	        } catch (IOException e) {
//	            logger.error("http接口调用异常：url is::" + url, e);
	        }
	    }
	    return result;
	}
}