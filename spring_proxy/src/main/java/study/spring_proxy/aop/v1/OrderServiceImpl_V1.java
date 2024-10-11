package study.spring_proxy.aop.v1;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl_V1 implements OrderService_V1 {

    private final OrderRepository_V1 orderRepository;

    public OrderServiceImpl_V1(OrderRepository_V1 orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
