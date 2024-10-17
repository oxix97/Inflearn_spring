package study.spring_proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BasicTest {

    @Test
    void basicConfig() {
        var context = new AnnotationConfigApplicationContext(BasicConfig.class);

        var a = context.getBean("beanA", A.class);
        assertInstanceOf(A.class, a);

        assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean("beanB", B.class));
    }

    @Slf4j
    @Configuration
    static class BasicConfig {

        @Bean(name = "beanA")
        public A a() {
            return new A();
        }

//        @Bean(name = "beanB")
//        public B b() {
//            return new B();
//        }
    }

    @Slf4j
    static class A {
        public void helloA() {
            log.info("hello A");
        }
    }

    @Slf4j
    static class B {
        public void helloB() {
            log.info("hello B");
        }
    }
}
