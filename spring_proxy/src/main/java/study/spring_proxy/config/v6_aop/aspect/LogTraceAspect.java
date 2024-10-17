package study.spring_proxy.config.v6_aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import study.spring_proxy.trace.TraceStatus;
import study.spring_proxy.trace.logtrace.LogTrace;

@Slf4j
@Aspect
public class LogTraceAspect {

    private final LogTrace trace;

    public LogTraceAspect(LogTrace trace) {
        this.trace = trace;
    }

    //한개의 Advisor
    @Around("execution(* study.spring_proxy.aop..*(..)) && !execution(* study.spring_proxy.aop..noLog(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        TraceStatus status = null;
        try{
            String message = joinPoint.getSignature().toShortString();
            status = trace.begin(message);

            //로직 실행
            Object result = joinPoint.proceed();

            trace.end(status);
            return result;
        }catch (Exception e) {
            throw e;
        }
    }
}
