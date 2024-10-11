package study.spring_proxy.aop.v3;

import org.springframework.stereotype.Service;

@Service
public class OrderService_V3 {

    private final OrderRepository_V3 orderRepository;

    public OrderService_V3(OrderRepository_V3 orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
