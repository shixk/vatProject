package com.imooc.vat.aop;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class AspectLogger {

	private static final Logger log = Logger.getLogger(AspectLogger.class);
	
	@Pointcut(value = "execution(* com.imooc.vat.controller..*.*(..))")
	public void apiPointcut() {
	}

	@Around(value = "apiPointcut()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object returnObj = null;
		boolean flag=true;
		try {
			// 获取一些基础信息
			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			HttpServletRequest request = servletRequestAttributes.getRequest();
			String ip = request.getRemoteAddr();
			Object[] arguments = proceedingJoinPoint.getArgs();
			//String requestMessage = (String) arguments[0];
			// 验证md5加密key，如果正确，执行业务逻辑方法
			//returnObj = validateMd5AndExecute(proceedingJoinPoint, request, requestMessage);
			log.info("本次请求的ip来自："+ip);
			if(flag){
				//符合条件的时候，去执行业务逻辑
				returnObj=proceedingJoinPoint.proceed();
			}else{
				returnObj="false";
			}
			
			
		} catch (Throwable ex) {
			log.error(ex.getMessage(), ex);
		} 
		return returnObj;
	}
}
