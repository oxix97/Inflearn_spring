package study.spring_proxy.config.v1_proixy.concreate_proxy;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import study.spring_proxy.aop.v1.OrderController_V1;
import study.spring_proxy.aop.v2.OrderController_V2;
import study.spring_proxy.aop.v2.OrderService_V2;
import study.spring_proxy.trace.TraceStatus;
import study.spring_proxy.trace.logtrace.LogTrace;

@RequestMapping
@ResponseBody
public class OrderControllerConcreateProxy extends OrderController_V2 {

    private final OrderController_V2 target;
    private final LogTrace trace;

    public OrderControllerConcreateProxy(OrderController_V2 target, LogTrace trace) {
        super(null);
        this.target = target;
        this.trace = trace;
    }

    @Override
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            String result = target.request(itemId);
            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    @Override
    public String noLog() {
        return target.noLog();
    }
}
