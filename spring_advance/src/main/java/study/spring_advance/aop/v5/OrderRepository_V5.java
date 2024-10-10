package study.spring_advance.aop.v5;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import study.spring_advance.trace.callback.TraceTemplate;
import study.spring_advance.trace.logtrace.LogTrace;
import study.spring_advance.trace.template.AbstractTemplate;

@Slf4j
@Repository
public class OrderRepository_V5 {

    private final TraceTemplate template;

    public OrderRepository_V5(LogTrace trace) {
        this.template = new TraceTemplate(trace);
    }

    public void save(String itemId) {
        template.execute("OrderRepository.save()", () -> {
            isException(itemId);
            sleep(1000);
            return null;
        });
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void isException(String itemId) {
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생");
        }
    }
}
