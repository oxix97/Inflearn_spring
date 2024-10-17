package study.spring_proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BeanPostProcessorTest {

    @Test
    void basicConfig() {
        var context = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);

        var a = context.getBean("beanA", B.class);

        assertInstanceOf(B.class, a);
        assertThrows(BeanNotOfRequiredTypeException.class, () -> context.getBean("beanA", A.class));
    }

    @Slf4j
    @Configuration
    static class BeanPostProcessorConfig {

        @Bean(name = "beanA")
        public A a() {
            return new A();
        }

        @Bean
        public AToBPostProcessor helloPostProcessor() {
            return new AToBPostProcessor();
        }
    }

    @Slf4j
    static class A {
        public void hello() {
            log.info("hello A");
        }
    }

    @Slf4j
    static class B {
        public void hello() {
            log.info("hello B");
        }
    }

    @Slf4j
    static class AToBPostProcessor implements BeanPostProcessor {
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            log.info("beanName = {}, bean = {}", beanName, bean);
            if (bean instanceof A) return new B();
            return bean;
        }
    }
}
