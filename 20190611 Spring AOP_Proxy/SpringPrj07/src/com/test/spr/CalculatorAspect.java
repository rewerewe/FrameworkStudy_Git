/*====================================
 	CalculatorAspect.java
 	- 보조 업무 수행 클래스.
 	- 보조 업무 적용, 주 업무 호출
 ====================================*/

package com.test.spr;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

// ※ Spring AOP Proxy 클래스를 설계하기 위해서 
//    MethodInterceptor 인터페이스를 구현하는 클래스를 설계한다.

public class CalculatorAspect implements MethodInterceptor
{
	// 보조 업무 적용 및 주 업무 호출 과정 추가 → invoke : (행동을 촉발하기 위해) ~ 부르다
	@Override
	public Object invoke(MethodInvocation method) throws Throwable
	{
		// 보조 업무(cross-cutting concern) 설정
		//-- 시간 측정(Around Advice)
		Log log = LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("처리 시간 측정 시작 ------------------------------");
		
		// 주 업무(core concern) 호출 부분		
		Object result = method.proceed();
		
		sw.stop();
		log.info("처리 시간 측정 종료 ------------------------------");
		log.info(String.format("경과시간 : %s/1000초", sw.getTotalTimeMillis()));
	
		return result; 
	}
	
}
