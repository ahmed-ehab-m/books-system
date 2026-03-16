package com.global.book.service;


import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

@Component
public class PriceSchedule {
	
	Logger log=LoggerFactory.getLogger(PriceSchedule.class);
	
//	@Scheduled(cron= "${interval-in-cron}")
//	@Scheduled(fixedRate = 2000)
	@SchedulerLock(name="bookComputePrice")
	
	@Async
	public void computePrice() throws InterruptedException
	{
		Thread.sleep(4000);
		log.info("compute price "+LocalDateTime.now());
	}
	
//	@Scheduled(fixedRate = 2000)
	@SchedulerLock(name="computeDiscount")
	
	@Async
	public void computeDiscount() throws InterruptedException
	{
		Thread.sleep(4000);
		log.info("compute discount "+LocalDateTime.now());
	}
}
