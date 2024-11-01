package study.spring_aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class Aspect_V3 {

    @Pointcut("execution(* study.spring_aop.order..*(..))")
    private void allOrders() {} // -> pointcut signature : 메서드 이름과 파라미터 => 포인트컷 시그니처

    //클래스 이름 패턴이 *Service.java인 경우
    @Pointcut("execution(* *..*Service.*(..))")
    private void allService() {}

    // MEMO : Around의 target : pointcut, method : advice
    @Around("allOrders()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); //join point 시그니처

        return joinPoint.proceed();
    }

    // MEMO : order패키지의 하위 패키지 이며 클래스 이름 패턴이 *Service인 경우
    @Around("allOrders() && allService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[트랜잭션 시작] {} ", joinPoint.getSignature()); //join point 시그니처
        try {
            Object result = joinPoint.proceed();
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        }finally {
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }
}
