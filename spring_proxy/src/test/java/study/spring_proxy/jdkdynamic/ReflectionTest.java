package study.spring_proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection_V0() {
        Hello target = new Hello();

        log.info("start");
        String a = target.callA();
        log.info("a = {}", a);

        log.info("start");
        String b = target.callB();
        log.info("b = {}", b);

        log.info("end");
    }

    @Test
    void reflection_V1() throws Exception {
        //클래스 정보
        Class<?> hello = Class.forName("study.spring_proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        //callA(메서드)에 대한 정보
        Method methodA = hello.getMethod("callA");
        Object resultA = methodA.invoke(target);
        log.info("resultA = {}", resultA);

        //callB(메서드)에 대한 정보
        Method methodB = hello.getMethod("callB");
        Object resultB = methodB.invoke(target);
        log.info("resultB = {}", resultB);
    }

    @Test
    void reflection_V2() throws Exception {
        Class<?> hello = Class.forName("study.spring_proxy.jdkdynamic.ReflectionTest$Hello");
        Hello target = new Hello();

        Method methodA = hello.getMethod("callA");
        dynamicCall(methodA, target);

        Method methodB = hello.getMethod("callB");
        dynamicCall(methodB, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result = {}", result);
        log.info("end");
    }

    @Slf4j
    static class Hello {

        public String callA() {
            log.info("callA()");
            return "A";
        }

        public String callB() {
            log.info("callB()");
            return "B";
        }
    }
}
