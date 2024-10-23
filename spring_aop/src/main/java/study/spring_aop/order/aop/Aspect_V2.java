package study.spring_aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class Aspect_V2 {

    @Pointcut("execution(* study.spring_aop.order..*(..))")
    private void allOrders(){} // -> pointcut signature : 메서드 이름과 파라미터 => 포인트컷 시그니처

    // MEMO : Around의 target : pointcut, method : advice
    @Around("allOrders()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); //join point 시그니처

        return joinPoint.proceed();
    }
}
