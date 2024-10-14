package study.spring_proxy.config.v1_proixy.concreate_proxy;

import study.spring_proxy.aop.v2.OrderService_V2;
import study.spring_proxy.trace.TraceStatus;
import study.spring_proxy.trace.logtrace.LogTrace;

public class OrderServiceConcreateProxy extends OrderService_V2 {

    private final OrderService_V2 target;
    private final LogTrace trace;

    public OrderServiceConcreateProxy(
            OrderService_V2 target,
            LogTrace trace
    ) {
        super(null); // 클래스 기반 프록시의 단점
        this.target = target;
        this.trace = trace;
    }

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem()");
            target.orderItem(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
