package com.dby.njxinch.quzrtz;

import com.dby.njxinch.services.IPushMessageService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LogisticsQuartz{
	
	IPushMessageService pushMessageServiceImpl;

	@Scheduled(cron="0 0/30 * * * ? ") //间隔30分钟
	public void doExePush() {
		System.out.println("============================定时任务执行============");
//		pushMessageServiceImpl.updateExePush();
	}
}
