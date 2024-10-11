package study.spring_proxy.aop.v2;

public class OrderService_V2 {

    private final OrderRepository_V2 orderRepository;

    public OrderService_V2(OrderRepository_V2 orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
