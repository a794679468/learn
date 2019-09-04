package com.dgq.consumer.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dgq.consumer.util.HttpClient;

@RestController
public class LuckController{
	
	@RequestMapping(value = "getluck",method = RequestMethod.GET)
	public ResponseEntity<?> getLuck(){
		
		return new ResponseEntity<Object>("我是伟宁", HttpStatus.OK);
		
	}
	
	public static void main(String[] args){
		System.out.println(HttpClient.httpPostUrlForPdf("http://kaijiang.500.com/shtml/ssq/19001.shtml"));
	}
}