package study.spring_advance.aop.v2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import study.spring_advance.trace.TraceId;
import study.spring_advance.trace.TraceStatus;
import study.spring_advance.trace.hellotrace.HelloTrace_V2;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderRepository_V2 {

    private final HelloTrace_V2 trace;

    public void save(TraceId traceId, String itemId) {

        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId,"OrderRepository.save()");
            saveLogic(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    private void saveLogic(String itemId) {
        if(itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생!");
        }
        sleep(1000);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
