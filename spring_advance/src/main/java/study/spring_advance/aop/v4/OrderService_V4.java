package study.spring_advance.aop.v4;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.spring_advance.trace.TraceStatus;
import study.spring_advance.trace.logtrace.LogTrace;
import study.spring_advance.trace.template.AbstractTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService_V4 {

    private final OrderRepository_V4 orderRepository;
    private final LogTrace trace;

    public void orderItem( String itemId) {
        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                orderRepository.save(itemId);
                return null;
            }
        };
        template.execute("OrderService.orderItem()");
    }
}
