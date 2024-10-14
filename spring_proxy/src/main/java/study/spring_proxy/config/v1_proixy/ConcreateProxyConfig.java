package study.spring_proxy.config.v1_proixy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring_proxy.aop.v2.OrderController_V2;
import study.spring_proxy.aop.v2.OrderRepository_V2;
import study.spring_proxy.aop.v2.OrderService_V2;
import study.spring_proxy.config.v1_proixy.concreate_proxy.OrderControllerConcreateProxy;
import study.spring_proxy.config.v1_proixy.concreate_proxy.OrderRepositoryConcreateProxy;
import study.spring_proxy.config.v1_proixy.concreate_proxy.OrderServiceConcreateProxy;
import study.spring_proxy.trace.logtrace.LogTrace;

@Configuration
public class ConcreateProxyConfig {

    @Bean
    public OrderController_V2 orderController_V2(LogTrace trace) {
        OrderController_V2 controller = new OrderController_V2(orderService_V2(trace));
        return new OrderControllerConcreateProxy(controller, trace);
    }

    @Bean
    public OrderService_V2 orderService_V2(LogTrace trace) {
        OrderService_V2 service = new OrderService_V2(orderRepository_V2(trace));
        return new OrderServiceConcreateProxy(service, trace);
    }

    @Bean
    public OrderRepository_V2 orderRepository_V2(LogTrace trace) {
        OrderRepository_V2 repository = new OrderRepository_V2();
        return new OrderRepositoryConcreateProxy(repository, trace);
    }
}
