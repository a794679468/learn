package com.dgq.consumer.service;

import java.util.List;
import java.util.Map;

public interface LuckService{
	
	public Map<String,Object> insert() throws Exception;
	
	
	public Map<String,Object> getNewLuck();

	public List<Map<String,Object>> getAllLuck();
	
	public String getRandomLuck();
	
}