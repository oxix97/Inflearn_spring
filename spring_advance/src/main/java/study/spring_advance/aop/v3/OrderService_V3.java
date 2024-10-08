package study.spring_advance.aop.v3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.spring_advance.trace.TraceId;
import study.spring_advance.trace.TraceStatus;
import study.spring_advance.trace.hellotrace.HelloTrace_V2;
import study.spring_advance.trace.logtrace.LogTrace;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService_V3 {

    private final OrderRepository_V3 orderRepository;
    private final LogTrace trace;

    public void orderItem( String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem()");
            orderRepository.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
