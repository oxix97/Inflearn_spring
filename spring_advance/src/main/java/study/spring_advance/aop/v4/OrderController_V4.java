package study.spring_advance.aop.v4;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring_advance.trace.TraceStatus;
import study.spring_advance.trace.logtrace.LogTrace;
import study.spring_advance.trace.template.AbstractTemplate;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v4/orders")
@RestController
public class OrderController_V4 {

    private final OrderService_V4 orderService;
    private final LogTrace trace;

    @GetMapping("/{itemId}")
    public String order(@PathVariable String itemId) {
        AbstractTemplate<String> template = new AbstractTemplate<>(trace) {
            @Override
            protected String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        };
        return template.execute("OrderController.request()");
    }
}
