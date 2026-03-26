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
@Order(1)
@Component
public class MeasurmentAspect {
	
	Logger log=LoggerFactory.getLogger(MeasurmentAspect.class);
	
	//
	// fully monitor the function
	// before and in and after
	// execution => means is second of execution of function
	// and give him filter expression (point cut) to determine this advice scope will be
	// return type + path + name of class +name of function + parameters of function
//	@Around(value = "execution(* com.global.book.service.*.*(..))")
	@Around(value = "execution(* com.global.book.base.BaseService.*(..))")
	public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable
	{
		long startTime =System.currentTimeMillis();
		StringBuilder sb =new StringBuilder("KPI:");
		sb.append("[").append(joinPoint.getKind()).append("]\tfor: ").append(joinPoint.getSignature())
		.append("\twithArgs: ").append("(").append(Arrays.toString(joinPoint.getArgs())).append(",").append(")");
		sb.append("\ttook: ");
		Object returnValue=joinPoint.proceed();
		log.info(sb.append((System.currentTimeMillis()- startTime)).append(" ms.").toString());
	    return returnValue;
	}
	////////////////////////////
	
	
}
