package kr.co.tiles.common.log;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAdvice {

	private static final Logger logger = LoggerFactory.getLogger("LoggingAdvice.class");

	// target 메서드의 파라미터 정보 출력함
//	@Before("execution(* kr.co.tiles.*.service.*.*(..)) or " + "execution(* kr.co.tiles.*.dao.*.*(..))")
	public void startLog(JoinPoint joinPoint) {
		logger.info("---------------------------------------------");
		logger.info("---------------------------------------------");

		// 전달되는 모든 파라미터들을 Object의 배열로 가져옴.
		logger.info("1: " + Arrays.toString(joinPoint.getArgs()));

		// 해당 Adivce의 타입을 알아냄
		logger.info("2: " + joinPoint.getKind());

		// 실행되는 대상 객체의 메소드에 대한 정보를 알아냄
		logger.info("3: " + joinPoint.getSignature().getName());

		// target 객체를 알아냄
		logger.info("4: " + joinPoint.getTarget().toString());
	}
	
//	@After("execution(* kr.co.tiles.*.service.*.*(..)) or " + "execution(* kr.co.tiles.*.dao.*.*(..))")
	public void after(JoinPoint joinPoint) {
		logger.info("+++++++++++++++++++++++++++++++++++++++++++++++");
		logger.info("+++++++++++++++++++++++++++++++++++++++++++++++");
		
		// 전달되는 모든 파라미터들을 Object의 배열로 가져옴.
		logger.info("1: " + Arrays.toString(joinPoint.getArgs()));

		// 해당 Adivce의 타입을 알아냄
		logger.info("2: " + joinPoint.getKind());

		// 실행되는 대상 객체의 메소드에 대한 정보를 알아냄
		logger.info("3: " + joinPoint.getSignature().getName());

		// target 객체를 알아냄
		logger.info("4: " + joinPoint.getTarget().toString());
	}
	
	@Around("execution(* kr.co.tiles.*.service.*.*(..)) or " + "execution(* kr.co.tiles.*.dao.*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp)throws Throwable {
		
		long startTime = System.currentTimeMillis();
		logger.info(Arrays.toString(pjp.getArgs()));
		
		// 실제 타켓을 실행하는 부분
		// 이 부분이 없으면 advice가 적용된 메서드가 동작하지 않음.
		Object result = pjp.proceed();
		
		long endTime = System.currentTimeMillis();
		
		// target 메서드의 동작 시간 출력
		logger.info(pjp.getSignature().getName() +" : "+(endTime-startTime));
		logger.info("----------------------------------------------------");
		
		// Around를 사용할 경우 반드시 object를 리턴해야 함.
		return result;
	}
}













