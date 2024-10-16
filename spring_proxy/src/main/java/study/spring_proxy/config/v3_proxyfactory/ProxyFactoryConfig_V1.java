package study.spring_proxy.config.v3_proxyfactory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring_proxy.aop.v1.*;
import study.spring_proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import study.spring_proxy.trace.logtrace.LogTrace;

@Slf4j
@Configuration
public class ProxyFactoryConfig_V1 {

    @Bean
    public OrderController_V1 orderControllerV1(LogTrace trace) {
        OrderControllerImpl_V1 controller = new OrderControllerImpl_V1(orderServiceV1(trace));
        ProxyFactory factory = new ProxyFactory(controller);
        factory.addAdvisor(getAdvisor(trace));

        OrderController_V1 proxy = (OrderController_V1) factory.getProxy();
        log.info("ProxyFactory proxy={}", proxy.getClass());
        return proxy;
    }

    @Bean
    public OrderService_V1 orderServiceV1(LogTrace trace) {
        OrderServiceImpl_V1 service = new OrderServiceImpl_V1(orderRepositoryV1(trace));
        ProxyFactory proxyFactory = new ProxyFactory(service);
        proxyFactory.addAdvisor(getAdvisor(trace));

        OrderService_V1 proxy = (OrderService_V1) proxyFactory.getProxy();
        log.info("ProxyFactory proxy={}", proxy.getClass());
        return proxy;
    }

    @Bean
    public OrderRepository_V1 orderRepositoryV1(LogTrace trace) {
        OrderRepositoryImpl_V1 repository = new OrderRepositoryImpl_V1();
        ProxyFactory factory = new ProxyFactory(repository);
        factory.addAdvisor(getAdvisor(trace));

        OrderRepository_V1 proxy = (OrderRepository_V1) factory.getProxy();
        log.info("ProxyFactory proxy={}", proxy.getClass());
        return proxy;
    }

    private Advisor getAdvisor(LogTrace trace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("save*", "order*", "request*");
        LogTraceAdvice advice = new LogTraceAdvice(trace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
