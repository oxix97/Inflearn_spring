package study.spring_proxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring_proxy.aop.v1.*;

@Configuration
public class AppConfig_V1 {

    @Bean
    public OrderController_V1 orderControllerV1() {
        return new OrderControllerImpl_V1(orderServiceV1());
    }

    @Bean
    public OrderService_V1 orderServiceV1() {
        return new OrderServiceImpl_V1(orderRepositoryV1());
    }

    @Bean
    public OrderRepository_V1 orderRepositoryV1() {
        return new OrderRepositoryImpl_V1();
    }
}
