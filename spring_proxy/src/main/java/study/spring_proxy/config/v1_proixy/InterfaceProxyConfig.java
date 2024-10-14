package study.spring_proxy.config.v1_proixy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring_proxy.aop.v1.*;
import study.spring_proxy.config.v1_proixy.interface_proxy.OrderControllerInterfaceProxy;
import study.spring_proxy.config.v1_proixy.interface_proxy.OrderRepositoryInterfaceProxy;
import study.spring_proxy.config.v1_proixy.interface_proxy.OrderServiceInterfaceProxy;
import study.spring_proxy.trace.logtrace.LogTrace;

@Configuration
public class InterfaceProxyConfig {

    @Bean
    public OrderController_V1 orderController(LogTrace trace) {
        OrderControllerImpl_V1 controller = new OrderControllerImpl_V1(orderService(trace));
        return new OrderControllerInterfaceProxy(controller, trace);
    }

    @Bean
    public OrderService_V1 orderService(LogTrace trace) {
        OrderServiceImpl_V1 service = new OrderServiceImpl_V1(orderRepository(trace));
        return new OrderServiceInterfaceProxy(service, trace);
    }

    @Bean
    public OrderRepository_V1 orderRepository(LogTrace trace) {
        OrderRepositoryImpl_V1 repository = new OrderRepositoryImpl_V1();
        return new OrderRepositoryInterfaceProxy(repository, trace);
    }
}
