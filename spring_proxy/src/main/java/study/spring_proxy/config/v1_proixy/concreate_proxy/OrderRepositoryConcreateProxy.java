package study.spring_proxy.config.v1_proixy.concreate_proxy;

import lombok.RequiredArgsConstructor;
import study.spring_proxy.aop.v2.OrderRepository_V2;
import study.spring_proxy.trace.TraceStatus;
import study.spring_proxy.trace.logtrace.LogTrace;

@RequiredArgsConstructor
public class OrderRepositoryConcreateProxy extends OrderRepository_V2 {

    private final OrderRepository_V2 target;
    private final LogTrace trace;

    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try{
            status = trace.begin("OrderRepository.save()");
            target.save(itemId);
            trace.end(status);
        }catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    }
}
