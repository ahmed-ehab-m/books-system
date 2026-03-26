package com.global.book.service;


import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.global.book.repository.AuthorRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

@Component

@Log4j2 // give me a log variable to use it in code
public class PriceSchedule {
	
	
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
