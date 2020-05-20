package com.dgq.consumer.job;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dgq.consumer.service.LuckService;
import com.dgq.consumer.util.LuckUtil;

@Component
@EnableScheduling
public class EverdayLuckPredictions{
	private Logger logger =LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LuckService luckservice;
	
	/**
	 * 定时任务每天定时预测
	 * @throws Exception 
	 */
//	@Scheduled(cron = "0 0 8 * * 1,3,5")
	@Scheduled(cron = " 0 20 11 0/1 * ?")
    public void predictions() throws Exception{
		String luck = LuckUtil.printCP("SSQ");
        logger.info("测试周是否执行" + LocalDate.now());
    }
}