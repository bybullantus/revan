package com.bc.revan.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Aspect
//@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

	
	@AfterThrowing("execution(* com.bc.revan.*.*.*(..))")
	public void WriteToLog(JoinPoint joinPoint) {
		System.out.println("Aspect  "+joinPoint.getSignature());
        logger.error("Aspect: " + joinPoint.getSignature());
	}
}