package study.spring_aop.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import study.spring_aop.member.MemberService;
import study.spring_aop.member.annotation.ClassAop;
import study.spring_aop.member.annotation.MethodAop;

@Slf4j
@Import(ParameterTest.ParameterAspect.class)
@SpringBootTest
public class ParameterTest {

    @Autowired
    MemberService memberService;

    @Test
    void success() {
        log.info("member service proxy = {}", memberService.getClass());
        memberService.hello("helloA");
    }


    @Slf4j
    @Aspect
    static class ParameterAspect {
        @Pointcut("execution(* study.spring_aop..*(..))")
        private void allMember() {
        }

        @Around("allMember()")
        public Object logArgs1(ProceedingJoinPoint joinPoint) throws Throwable {
            Object arg = joinPoint.getArgs()[0];
            log.info("[args 1] {}, arg = {}", joinPoint.getSignature(), arg);
            return joinPoint.proceed();
        }

        @Around("allMember() && args(arg,..)")
        public Object logArgs2(ProceedingJoinPoint joinPoint, Object arg) throws Throwable {
            log.info("[args 2] {}, arg = {}", joinPoint.getSignature(), arg);
            return joinPoint.proceed();
        }

        @Before("allMember() && args(arg,..)")
        public void logArgs3(String arg) {
            log.info("[args 3] arg = {}", arg);
        }

        // MEMO : 실제 객체
        @Before("allMember() && target(obj)")
        public void targetArgs(JoinPoint joinPoint, MemberService obj) {
            log.info("[target] {}, obj = {}", joinPoint.getSignature(), obj.getClass());
        }

        // MEMO : Spring container에 적용된 객체 -> proxy 객체
        @Before(value = "allMember() && this(obj)", argNames = "joinPoint,obj")
        public void thisArgs(JoinPoint joinPoint, MemberService obj) {
            log.info("[this] {}, obj = {}", joinPoint.getSignature(), obj.getClass());
        }

        @Before(value = "allMember() && @target(annotation)")
        public void atTarget(JoinPoint joinPoint, ClassAop annotation) {
            log.info("[@target] {}, obj = {}", joinPoint.getSignature(), annotation.getClass());
        }

        @Before(value = "allMember() && @within(annotation)")
        public void atWithin(JoinPoint joinPoint, ClassAop annotation) {
            log.info("[@within] {}, obj = {}", joinPoint.getSignature(), annotation.getClass());
        }

        @Before(value = "allMember() && @annotation(annotation)")
        public void atAnnotation(JoinPoint joinPoint, MethodAop annotation) {
            log.info("[@annotation] {}, annotation value = {}", joinPoint.getSignature(), annotation.value());
        }
    }
}
