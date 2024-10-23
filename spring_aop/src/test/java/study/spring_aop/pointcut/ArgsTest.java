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
public class ArgsTest {

    Method method;

    @BeforeEach
    void init() throws NoSuchMethodException {
        method = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void args() {
        assertTrue(pointcut("args(String)").matches(method, MemberServiceImpl.class));
        assertTrue(pointcut("args(Object)").matches(method, MemberServiceImpl.class));
        assertFalse(pointcut("args()").matches(method, MemberServiceImpl.class));
        assertTrue(pointcut("args(..)").matches(method, MemberServiceImpl.class));
        assertTrue(pointcut("args(*)").matches(method, MemberServiceImpl.class));
        assertTrue(pointcut("args(String, ..)").matches(method, MemberServiceImpl.class));
    }

    /**
     * execution(* * (java.io.Serializable)): 메서드의 시그니처로 판단 (정적) -> 정확하게 매칭되야함
     * args(java.io.Serializable): 런타임에 전달된 인수로 판단(동적) -> 상위 타입도 허용
     */
    @Test
    void argsVsExecution() {
        assertTrue(pointcut("execution(* *(String))").matches(method, MemberServiceImpl.class));
        assertFalse(pointcut("execution(* *(java.io.Serializable))").matches(method, MemberServiceImpl.class));
        assertFalse(pointcut("execution(* *(Object))").matches(method, MemberServiceImpl.class));
    }

    private AspectJExpressionPointcut pointcut(String expression) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        return pointcut;
    }
}
