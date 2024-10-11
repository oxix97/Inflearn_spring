package study.spring_proxy.aop.v1;

public class OrderControllerImpl_V1 implements OrderController_V1 {

    private final OrderService_V1 orderService;

    public OrderControllerImpl_V1(OrderService_V1 orderService) {
        this.orderService = orderService;
    }

    @Override
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @Override
    public String noLog() {
        return "ok";
    }
}
