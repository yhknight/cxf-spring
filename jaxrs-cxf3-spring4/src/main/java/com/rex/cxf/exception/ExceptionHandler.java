package com.rex.cxf.exception;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionHandler  {
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	HttpServletRequest req;
	
	@Pointcut("execution(* com.rex.cxf..*.*(..))")
	public void pointCut(){
		
	}
	
	@AfterThrowing(pointcut = "pointCut()", throwing = "ex")
	public void afterThrowing(Exception ex){
		System.out.println(this.messageSource.getMessage(ex.getMessage(), new String[]{"WRONG!!"}, req.getLocale()));
	}

}
