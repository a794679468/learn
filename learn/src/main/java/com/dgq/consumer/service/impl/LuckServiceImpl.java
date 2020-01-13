package com.dgq.consumer.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dgq.consumer.dao.LuckDao;
import com.dgq.consumer.service.LuckService;
import com.dgq.consumer.util.HttpClient;
import com.dgq.consumer.util.LuckUtil;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

@Service
public class LuckServiceImpl implements LuckService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final static String URL = "https://m.aicai.com/kjgg/detailAll.do?agentId=2335098&vt=5&gameId=101&issueNo=";  
	
	@Autowired
	private LuckDao luckdao;
	
	public Map<String,Object> insert() throws Exception{
		
		Map<String,Object> map =Maps.newHashMap();
		Map<String,Object> newlcuk = luckdao.getNewLuck();
		int a = 2003;
		int b = 0;
		if(!newlcuk.isEmpty()){
			a = Integer.valueOf(newlcuk.get("everynper").toString().substring(0, 4));
			b = Integer.valueOf(newlcuk.get("everynper").toString().substring(4, 7));
		}
		String period = "";
		int thisyear = Integer.valueOf(LocalDate.now().toString().split("-")[0]);
		while(true){
			Thread.sleep(5000);
			b++;
			String end = Strings.padStart(b+"", 3, '0');
			period= a + end;
			String hmtl = HttpClient.httpPostUrlForPdf(URL+period);
			Document document = Jsoup.parse(hmtl);
			Elements spans= document.select("div[class=lotteryBall]").select("em");
			String c = "";
			for(Element els:spans){
				if(els.toString().contains("blue")){
					c = c.substring(0, c.lastIndexOf(",")) + "-" + els.text();
				}else{
					c += els.text()+",";
				}
			}
			Map<String,Object> inparam = Maps.newHashMap();
			inparam.put("everynper", period);
			inparam.put("everyluck", c);
			if(c.equals("{{redBall}}-{{blueBall}}")){
				a++;
				b = 0;
				if(a == thisyear){
					continue;
				}else if( a > thisyear){
					break;
				}
			}else{
				Boolean res =  luckdao.insertLuck(inparam);
				if(res){
					logger.info(period +"期："+ c +"插入成功");
				}
			}
		}
		logger.info("全部插入完成");
		return map;
		
	}

	@Override
	public Map<String, Object> getNewLuck() {
		return luckdao.getNewLuck();
	}

	@Override
	public String getRandomLuck() {
		String b = "";
		String a = "";
		int c = 0; 
		String d = "";
		List<Map<String, Object>> historylist = luckdao.getAllLuck();
		for(Map<String, Object> map:historylist){
			d = d + "," + map.get("everyluck");
		}
		while(true){
			 a = LuckUtil.printCP("SSQ");
			if(d.contains(a)){
				logger.info(a);
				break;
			}
			b= b+","+a;
			c++;
			logger.info(c+"");
		}
//		if(d.contains(a)){
//			this.getRandomLuck();
//		}
		return a;
	}

	public List<Map<String, Object>> getAllLuck() {
		return luckdao.getAllLuck();
	}

}