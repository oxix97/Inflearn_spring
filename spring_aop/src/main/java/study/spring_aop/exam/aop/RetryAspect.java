package study.spring_aop.exam.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import study.spring_aop.exam.annotation.Retry;

@Slf4j
@Aspect
public class RetryAspect {

    @Around("@annotation(retry)")
    public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        log.info("[retry] {} args = {}", joinPoint.getSignature(), retry);
        int maxRetry = retry.value();
        Exception exceptionHolder = null;

        for (int i = 1; i <= maxRetry; i++) {
            try {
                return joinPoint.proceed();
            } catch (Exception e) {
                log.info("[retry] try count = {}/{}", i, maxRetry);
                exceptionHolder = e;
            }
        }
        throw exceptionHolder;
    }
}
