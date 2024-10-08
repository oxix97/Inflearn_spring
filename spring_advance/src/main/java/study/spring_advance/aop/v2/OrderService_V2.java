package study.spring_advance.aop.v2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.spring_advance.trace.TraceId;
import study.spring_advance.trace.TraceStatus;
import study.spring_advance.trace.hellotrace.HelloTrace_V2;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService_V2 {

    private final OrderRepository_V2 orderRepository;
    private final HelloTrace_V2 trace;

    public void orderItem(TraceId traceId, String itemId) {
        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId,"OrderService.orderItem()");
            orderRepository.save(status.getTraceId(),itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
