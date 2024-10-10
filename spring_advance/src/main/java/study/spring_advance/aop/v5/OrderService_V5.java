package study.spring_advance.aop.v5;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.spring_advance.trace.callback.TraceTemplate;
import study.spring_advance.trace.logtrace.LogTrace;

@Slf4j
@Service
public class OrderService_V5 {

    private final OrderRepository_V5 orderRepository;
    private final TraceTemplate template;

    public OrderService_V5(OrderRepository_V5 orderRepository, LogTrace trace) {
        this.orderRepository = orderRepository;
        this.template = new TraceTemplate(trace);
    }

    public void orderItem(String itemId) {
        template.execute("OrderService.orderItem()", () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
