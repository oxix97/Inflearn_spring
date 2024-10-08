package study.spring_advance.aop.v2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring_advance.trace.TraceStatus;
import study.spring_advance.trace.hellotrace.HelloTrace_V2;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v2/orders")
@RestController
public class OrderController_V2 {

    private final OrderService_V2 orderService;
    private final HelloTrace_V2 trace;

    @GetMapping("/{itemId}")
    public String order(@PathVariable String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(status.getTraceId(),itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
