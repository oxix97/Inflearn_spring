package study.spring_advance.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import study.spring_advance.trace.strategy.code.Callback;
import study.spring_advance.trace.strategy.code.TimeLogTemplate;

import java.sql.Time;

@Slf4j
public class TemplateCallbackTest {

    @Test
    void callback_V1() {
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(() -> log.info("비즈니스 로직1 실행"));
        template.execute(() -> log.info("비즈니스 로직2 실행"));
    }
}
