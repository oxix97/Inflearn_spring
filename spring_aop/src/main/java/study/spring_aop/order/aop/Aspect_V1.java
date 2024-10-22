package study.spring_aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class Aspect_V1 {

    // MEMO : Around의 target : pointcut, method : advice
    @Around("execution(* study.spring_aop.order..*(..))")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[start] {}", joinPoint.getSignature()); //join point 시그니처

        return joinPoint.proceed();
    }
}
