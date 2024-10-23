package study.spring_aop.internal.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class CallLogAspect {

    @Before("execution(* study.spring_aop.internal..*.*(..))")
    public void doLog(JoinPoint joinPoint) {
        log.info("aop = {}", joinPoint.getSignature());
    }

}
