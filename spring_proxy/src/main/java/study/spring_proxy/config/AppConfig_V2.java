package study.spring_proxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring_proxy.aop.v2.OrderController_V2;
import study.spring_proxy.aop.v2.OrderRepository_V2;
import study.spring_proxy.aop.v2.OrderService_V2;

@Configuration
public class AppConfig_V2 {

    @Bean
    public OrderController_V2 orderControllerV2() {
        return new OrderController_V2(orderServiceV2());
    }

    @Bean
    public OrderService_V2 orderServiceV2() {
        return new OrderService_V2(orderRepositoryV2());
    }

    @Bean
    public OrderRepository_V2 orderRepositoryV2() {
        return new OrderRepository_V2();
    }
}
