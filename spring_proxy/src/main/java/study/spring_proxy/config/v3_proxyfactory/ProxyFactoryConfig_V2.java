package study.spring_proxy.config.v3_proxyfactory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring_proxy.aop.v2.OrderController_V2;
import study.spring_proxy.aop.v2.OrderRepository_V2;
import study.spring_proxy.aop.v2.OrderService_V2;
import study.spring_proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import study.spring_proxy.trace.logtrace.LogTrace;

@Slf4j
@Configuration
public class ProxyFactoryConfig_V2 {

    @Bean
    public OrderController_V2 orderControllerV2(LogTrace trace) {
        OrderController_V2 controller = new OrderController_V2(orderServiceV2(trace));
        ProxyFactory factory = new ProxyFactory(controller);
        factory.addAdvisor(getAdvisor(trace));

        OrderController_V2 proxy = (OrderController_V2) factory.getProxy();
        log.info("ProxyFactory proxy={}", proxy.getClass());
        return proxy;
    }

    @Bean
    public OrderService_V2 orderServiceV2(LogTrace trace) {
        OrderService_V2 service = new OrderService_V2(orderRepositoryV2(trace));
        ProxyFactory proxyFactory = new ProxyFactory(service);
        proxyFactory.addAdvisor(getAdvisor(trace));

        OrderService_V2 proxy = (OrderService_V2) proxyFactory.getProxy();
        log.info("ProxyFactory proxy={}", proxy.getClass());
        return proxy;
    }

    @Bean
    public OrderRepository_V2 orderRepositoryV2(LogTrace trace) {
        OrderRepository_V2 repository = new OrderRepository_V2();
        ProxyFactory factory = new ProxyFactory(repository);
        factory.addAdvisor(getAdvisor(trace));

        OrderRepository_V2 proxy = (OrderRepository_V2) factory.getProxy();
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
