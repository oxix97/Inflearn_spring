package study.spring_proxy.config.v2_dynamicproxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring_proxy.aop.v1.*;
import study.spring_proxy.trace.logtrace.LogTrace;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyFilterConfig {

    private static final String[] PATTERNS = {
            "request*", "order*", "save*"
    };

    @Bean
    public OrderController_V1 orderControllerV1(LogTrace trace) {
        OrderControllerImpl_V1 controller = new OrderControllerImpl_V1(orderServiceV1(trace));
        OrderController_V1 proxy = (OrderController_V1) Proxy.newProxyInstance(
                OrderController_V1.class.getClassLoader(),
                new Class[]{OrderController_V1.class},
                new LogTraceFilterHandler(controller, trace, PATTERNS)
        );
        return proxy;
    }

    @Bean
    public OrderService_V1 orderServiceV1(LogTrace trace) {
        OrderServiceImpl_V1 service = new OrderServiceImpl_V1(orderRepositoryV1(trace));
        OrderService_V1 proxy = (OrderService_V1) Proxy.newProxyInstance(
                OrderService_V1.class.getClassLoader(),
                new Class[]{OrderService_V1.class},
                new LogTraceFilterHandler(service, trace, PATTERNS)
        );
        return proxy;
    }

    @Bean
    public OrderRepository_V1 orderRepositoryV1(LogTrace trace) {
        OrderRepositoryImpl_V1 repository = new OrderRepositoryImpl_V1();
        OrderRepository_V1 proxy = (OrderRepository_V1) Proxy.newProxyInstance(
                OrderRepository_V1.class.getClassLoader(),
                new Class[]{OrderRepository_V1.class},
                new LogTraceFilterHandler(repository, trace, PATTERNS)
        );
        return proxy;
    }
}
