package study.spring_proxy.config.v3_proxyfactory.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import study.spring_proxy.trace.TraceStatus;
import study.spring_proxy.trace.logtrace.LogTrace;

import java.lang.reflect.Method;

@Slf4j
@RequiredArgsConstructor
public class LogTraceAdvice implements MethodInterceptor {

    private final LogTrace trace;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        TraceStatus status = null;
        try {
            Method method = invocation.getMethod();
            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            status = trace.begin(message);

            //로직 호출
            Object result = invocation.proceed();

            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
