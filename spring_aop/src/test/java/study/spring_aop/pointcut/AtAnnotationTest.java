package study.spring_aop.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import study.spring_aop.member.MemberService;

@Slf4j
@SpringBootTest
public class AtAnnotationTest {

    @Autowired
    MemberService memberService;

    @Pointcut("@annotation(study.spring_aop.member.annotation.MethodAop)")
    private void annotationPointcut(){}

    @Test
    void success() {
        log.info("member service proxy = {}", memberService.getClass());
        memberService.hello("helloA");
    }

    @TestConfiguration
    static class Config{
        @Bean
        public AtAnnotationAspect atAnnotationAspect() {
            return new AtAnnotationAspect();
        }
    }

    @Slf4j
    @Aspect
    static class AtAnnotationAspect {
        @Around("annotationPointcut()")
        public Object atAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@annotation] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}
