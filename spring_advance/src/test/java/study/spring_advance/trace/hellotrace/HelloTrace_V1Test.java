package study.spring_advance.trace.hellotrace;

import org.junit.jupiter.api.Test;
import study.spring_advance.trace.TraceStatus;

import static org.junit.jupiter.api.Assertions.*;


class HelloTrace_V1Test {
    @Test
    void begin_end() {
        HelloTrace_V1 trace = new HelloTrace_V1();
        TraceStatus status = trace.begin("hello1");
        trace.end(status);
    }

    @Test
    void begin_exception() {
        HelloTrace_V1 trace = new HelloTrace_V1();
        TraceStatus status = trace.begin("hello2");
        trace.exception(status, new IllegalStateException());
    }
}