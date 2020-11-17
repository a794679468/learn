package com.dgq.consumer.job;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dgq.consumer.service.LuckService;

@Component
@EnableScheduling
public class EverdayLuckDateUpdate{
	private Logger logger =LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LuckService luckservice;
	
	/**
	 * 定时任务每天定时插入最新数据
	 * @throws Exception 
	 */
	@Scheduled(cron = "0 0 0 * * 1,3,5")
    public void scheduled() throws Exception{
		luckservice.insert();
        logger.info("数据插入执行完成" + LocalDate.now());
    }
}