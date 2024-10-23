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
import study.spring_aop.member.annotation.ClassAop;

@Slf4j
@SpringBootTest
public class AtTargetAtWithinTest {

    // MEMO : 인스턴스의 모든 메서드를 조인포인트로 적용 -> 부모 클래스의 메서드까지 어드바이스 적용
    @Pointcut("@target(study.spring_aop.member.annotation.ClassAop)")
    private void targetAnnotation(){}
    // MEMO : 해당 타입 내에 있는 메서드만 조인 포인트로 적용 -> 자기 자신의 클래스에 정의된 메서드에만 어드바이스 적용

    @Pointcut("@within(study.spring_aop.member.annotation.ClassAop)")
    private void withinAnnotation(){}

    @Pointcut("execution(* study.spring_aop..*(..))")
    private void path(){}

    @Autowired
    Child child;

    @Test
    void success() {
        log.info("child proxy = {}", child.getClass());
        child.childMethod();
        log.info("==============================");
        child.parentMethod();
    }

    @TestConfiguration
    static class Config {

        @Bean
        public Parent parent() {
            return new Parent();
        }

        @Bean
        public Child child() {
            return new Child();
        }

        @Bean
        public AtTargetAtWithinAspect atTargetAtWithinAspect() {
            return new AtTargetAtWithinAspect();
        }
    }

    @Slf4j
    @Aspect
    static class AtTargetAtWithinAspect {

        @Around("path() && targetAnnotation()")
        public Object atTarget(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@target] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("path() && withinAnnotation()")
        public Object atWithin(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@within] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

    static class Parent {
        public void parentMethod() {
        } //부모에만 있는 메서드
    }

    @ClassAop
    static class Child extends Parent {
        public void childMethod() {
        }
    }
}
