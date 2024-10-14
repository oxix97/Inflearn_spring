package study.spring_proxy.config.v1_proixy.interface_proxy;

import lombok.RequiredArgsConstructor;
import study.spring_proxy.aop.v1.OrderController_V1;
import study.spring_proxy.trace.TraceStatus;
import study.spring_proxy.trace.logtrace.LogTrace;

@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements OrderController_V1 {

    private final OrderController_V1 target;
    private final LogTrace trace;

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
