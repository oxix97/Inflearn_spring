package study.spring_aop.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import study.spring_aop.member.MemberServiceImpl;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class ExecutionTest {


    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method method;

    @BeforeEach
    void init() throws NoSuchMethodException {
        method = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void printMethod() {
//        public java.lang.String study.spring_aop.member.MemberServiceImpl.hello(java.lang.String)
        log.info("method={}", method);
    }

    /**
     * @접근제어자: public
     * @반환타입: String
     * @선언타입: study.spring_aop.member.MemberServiceImpl
     * @메서드이름: hello
     * @파라미터: String
     * @예외: 생략
     */
    @Test
    void exactMatch() {
//        public java.lang.String study.spring_aop.member.MemberServiceImpl.hello(java.lang.String)
        pointcut.setExpression("execution(public String study.spring_aop.member.MemberServiceImpl.hello(String))");
        assertTrue(pointcut.matches(method, MemberServiceImpl.class));
    }

    /**
     * @접근제어자: 생략
     * @반환타입: *
     * @선언타입: 생략
     * @메서드이름: *
     * @파라미터: (..)
     * @예외: 없음
     */
    @Test
    void allMatch() {
        pointcut.setExpression("execution(* *(..))");
        assertTrue(pointcut.matches(method, MemberServiceImpl.class));
    }

    @Test
    void nameMatch() {
        pointcut.setExpression("execution(* hello(..))");
        assertTrue(pointcut.matches(method, MemberServiceImpl.class));
    }

    @Test
    void nameMatchStar1() {
        pointcut.setExpression("execution(* hel*(..))");
        assertTrue(pointcut.matches(method, MemberServiceImpl.class));
    }

    @Test
    void nameMatchStar2() {
        pointcut.setExpression("execution(* *llo(..))");
        assertTrue(pointcut.matches(method, MemberServiceImpl.class));
    }

    @Test
    void nameMatchStar3() {
        pointcut.setExpression("execution(* nono(..))");
        assertFalse(pointcut.matches(method, MemberServiceImpl.class));
    }

    @Test
    void packageExactMatch() {
        pointcut.setExpression("execution(* study.spring_aop.member.MemberServiceImpl.hello(..))");
        assertTrue(pointcut.matches(method, MemberServiceImpl.class));
    }

    @Test
    void packageMatch1() {
        pointcut.setExpression("execution(* study.spring_aop.member.*.*(..))");
        assertTrue(pointcut.matches(method, MemberServiceImpl.class));
    }

    @Test
    void packageMatchFalse() {
        pointcut.setExpression("execution(* study.spring_aop.*.*(..))");
        assertFalse(pointcut.matches(method, MemberServiceImpl.class));
    }

    @Test
    void packageMatchSubPackage1() {
        pointcut.setExpression("execution(* study.spring_aop.member..*.*(..))");
        assertTrue(pointcut.matches(method, MemberServiceImpl.class));
    }

    @Test
    void packageMatchSubPackage2() {
        pointcut.setExpression("execution(* study.spring_aop..*.*(..))");
        assertTrue(pointcut.matches(method, MemberServiceImpl.class));
    }

    @Test
    void typeExactMatch() {
        pointcut.setExpression("execution(* study.spring_aop.member.MemberServiceImpl.*(..))");
        assertTrue(pointcut.matches(method, MemberServiceImpl.class));
    }

    // MEMO : 부모 타입을 선언해도 해당 자식 타입은 매칭된다.
    @Test
    void typeSuperMatch() {
        pointcut.setExpression("execution(* study.spring_aop.member.MemberService.*(..))");
        assertTrue(pointcut.matches(method, MemberServiceImpl.class));
    }

    // MEMO : 자식 타입이 매칭되지만 부모 타입에 소속되어 있지 않은 메서드는 확인하지 못함
    @Test
    void typeMatchInternal1() throws NoSuchMethodException {
        pointcut.setExpression("execution(* study.spring_aop.member.MemberService.*(..))");
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        assertFalse(pointcut.matches(internalMethod, MemberServiceImpl.class));
    }

    @Test
    void typeMatchInternal2() throws NoSuchMethodException {
        pointcut.setExpression("execution(* study.spring_aop.member.MemberServiceImpl.*(..))");
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        assertTrue(pointcut.matches(internalMethod, MemberServiceImpl.class));
    }

    @Test
    void argsMatchString() {
        pointcut.setExpression("execution(* *(String))");
        assertTrue(pointcut.matches(method, MemberServiceImpl.class));
    }

    @Test
    void argsMatchNoArgs() {
        pointcut.setExpression("execution(* *())");
        assertFalse(pointcut.matches(method, MemberServiceImpl.class));
    }

    @Test
    void argsMatchStar() {
        pointcut.setExpression("execution(* *(*))");
        assertTrue(pointcut.matches(method, MemberServiceImpl.class));
    }

    @Test
    void argsMatchAll() {
        pointcut.setExpression("execution(* *(..))");
        assertTrue(pointcut.matches(method, MemberServiceImpl.class));
    }

    @Test
    void argsMatchComplex() {
        pointcut.setExpression("execution(* *(String, ..))");
        assertTrue(pointcut.matches(method, MemberServiceImpl.class));
    }
}
