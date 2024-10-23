package study.spring_aop.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import study.spring_aop.member.MemberService;
import study.spring_aop.member.MemberServiceImpl;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class WithinTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method method;

    @BeforeEach
    void init() throws NoSuchMethodException {
        method = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void withinExact(){
        pointcut.setExpression("within(study.spring_aop.member.MemberServiceImpl)");
        assertTrue(pointcut.matches(method, MemberServiceImpl.class));
    }

    @Test
    void withinStar(){
        pointcut.setExpression("within(study.spring_aop.member.*Service*)");
        assertTrue(pointcut.matches(method, MemberServiceImpl.class));
    }

    @Test
    void withinSubPackage(){
        pointcut.setExpression("within(study.spring_aop..*)");
        assertTrue(pointcut.matches(method, MemberServiceImpl.class));
    }

    @Test
    void withinSuperTypeFalse(){
        pointcut.setExpression("within(study.spring_aop.member.MemberService)");
        assertFalse(pointcut.matches(method, MemberServiceImpl.class));
    }

    @Test
    void executionSuperTypeTrue(){
        pointcut.setExpression("execution(* study.spring_aop.member.MemberService.*(..))");
        assertTrue(pointcut.matches(method, MemberServiceImpl.class));
    }
}
