package study.spring_advance.trace.hellotrace;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import study.spring_advance.trace.TraceStatus;

import static org.junit.jupiter.api.Assertions.*;

class HelloTrace_V2Test {

    @Test
    void begin_end() {
        HelloTrace_V2 trace = new HelloTrace_V2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception() {
        HelloTrace_V2 trace = new HelloTrace_V2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }

    @Test
    void begin_end2() {
        HelloTrace_V2 trace = new HelloTrace_V2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        TraceStatus status3 = trace.beginSync(status2.getTraceId(), "hello3");
        trace.end(status3);
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception2() {
        HelloTrace_V2 trace = new HelloTrace_V2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        TraceStatus status3 = trace.beginSync(status2.getTraceId(), "hello3");
        trace.exception(status3, new IllegalStateException());
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }
}
