package com.global.book.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect
@Order(0)
@Component
public class LogTimeAspect {
	
	Logger log=LoggerFactory.getLogger(LogTimeAspect.class);
	
	
	@Pointcut(value = "execution(* com.global.book.repository..*(..))")
	public void forRepoLog () {
		
	}
	
	@Pointcut(value = "execution(* com.global.book.service..*(..))")
	public void forServiceLog () {
		
	}
	
	@Pointcut(value = "execution(* com.global.book.controller..*(..))")
	public void forControllerLog () {
		
	}
	
	@Pointcut(value = "execution(* com.global.book.base..*(..))")
	public void forBaseLog() {}
	
	@Pointcut(value = "forRepoLog () || forServiceLog () || forControllerLog () || forBaseLog()")
	public void forAllApp () {
		
	}
	
	
	@Before(value = "forAllApp()")
	public void beforeMethod (JoinPoint joinPoint) {
		
		String methodName=joinPoint.getSignature().toShortString();
		
		log.info("Method name is =>>> "+methodName);
		Object [] args= joinPoint.getArgs();
		for(Object arg :args)
		{
			log.info(" with arguments is =>>> "+arg);

		}
	}
	
}
