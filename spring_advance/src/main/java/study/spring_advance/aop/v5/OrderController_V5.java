package study.spring_advance.aop.v5;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring_advance.trace.callback.TraceTemplate;
import study.spring_advance.trace.logtrace.LogTrace;

@Slf4j
@RequestMapping("/api/v5/orders")
@RestController
public class OrderController_V5 {

    private final OrderService_V5 orderService;
    private final TraceTemplate template;

    public OrderController_V5(OrderService_V5 orderService, LogTrace trace) {
        this.orderService = orderService;
        this.template = new TraceTemplate(trace);
    }

    @GetMapping("/{itemId}")
    public String order(@PathVariable String itemId) {
        return template.execute("OrderController.request()", () -> {
            orderService.orderItem(itemId);
            return "ok";
        });
    }
}
