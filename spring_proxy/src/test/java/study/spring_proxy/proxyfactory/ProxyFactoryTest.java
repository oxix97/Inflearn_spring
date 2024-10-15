package study.spring_proxy.proxyfactory;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import study.spring_proxy.common.advice.TimeAdvice;
import study.spring_proxy.common.service.ConcreateService;
import study.spring_proxy.common.service.ServiceImpl;
import study.spring_proxy.common.service.ServiceInterface;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class ProxyFactoryTest {

    @Test
    @DisplayName("인터페이스가 있으면 JDK 동적 프록시 적용")
    void interfaceProxy() {
        ServiceImpl target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        log.info("targetClass = {}", target.getClass().getName());
        log.info("proxyClass = {}", proxy.getClass().getName());

        proxy.save();

        assertTrue(AopUtils.isAopProxy(proxy));
        assertTrue(AopUtils.isJdkDynamicProxy(proxy));
        assertFalse(AopUtils.isCglibProxy(proxy));
    }

    @Test
    @DisplayName("구체 클래스만 있으면 CGLIB 사용")
    void concreateProxy() {
        ConcreateService target = new ConcreateService();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ConcreateService proxy = (ConcreateService) proxyFactory.getProxy();

        log.info("targetClass = {}", target.getClass().getName());
        log.info("proxyClass = {}", proxy.getClass().getName());

        proxy.call();

        assertTrue(AopUtils.isAopProxy(proxy));
        assertFalse(AopUtils.isJdkDynamicProxy(proxy));
        assertTrue(AopUtils.isCglibProxy(proxy));
    }

    @Test
    @DisplayName("ProxyTargetClass 옵션을 사용하면 인터페이스가 있어도 CGLIB를 사용, 클래스 기반 프록시 사용")
    void proxyTargetClass() {
        ServiceImpl target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.addAdvice(new TimeAdvice());

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        log.info("targetClass = {}", target.getClass().getName());
        log.info("proxyClass = {}", proxy.getClass().getName());

        proxy.save();

        assertTrue(AopUtils.isAopProxy(proxy));
        assertFalse(AopUtils.isJdkDynamicProxy(proxy));
        assertTrue(AopUtils.isCglibProxy(proxy));
    }
}
