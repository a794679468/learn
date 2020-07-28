package com.dgq.consumer.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

//@Repository
public interface LuckDao {

	public boolean insertLuck(Map<String,Object> map);

	public int getMaxLuck();
	
	public Map<String, Object> getNewLuck();
	
	public List<Map<String, Object>> getAllLuck();
	
	
}