package study.spring_proxy.config.v1_proixy.interface_proxy;

import lombok.RequiredArgsConstructor;
import study.spring_proxy.aop.v1.OrderRepository_V1;
import study.spring_proxy.trace.TraceStatus;
import study.spring_proxy.trace.logtrace.LogTrace;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements OrderRepository_V1 {
    private final OrderRepository_V1 target;
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
