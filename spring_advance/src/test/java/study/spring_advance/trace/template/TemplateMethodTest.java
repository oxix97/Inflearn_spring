package study.spring_advance.trace.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import study.spring_advance.trace.template.code.AbstractTemplate;
import study.spring_advance.trace.template.code.SubClassLogic1;
import study.spring_advance.trace.template.code.SubClassLogic2;

@Slf4j
public class TemplateMethodTest {
    @Test
    void templateMethod_V0() {
        logic1();
        logic2();
    }

    @Test
    void templateMethod_V1() {
        AbstractTemplate template1 = new SubClassLogic1();
        AbstractTemplate template2 = new SubClassLogic2();

        template1.execute();
        template2.execute();
    }

    @Test
    void templateMethod_V2() {
        AbstractTemplate template1 = new AbstractTemplate(){
            @Override
            protected void call() {
                log.info("logic 1");
            }
        };
        template1.execute();
    }

    private void logic1() {
        long start = System.currentTimeMillis();
        log.info("로직 1 실행");
        long end = System.currentTimeMillis();
        long resultTime = end - start;
        log.info("resultTime={}", resultTime);
    }

    private void logic2() {
        long start = System.currentTimeMillis();
        log.info("로직 2 실행");
        long end = System.currentTimeMillis();
        long resultTime = end - start;
        log.info("resultTime={}", resultTime);
    }
}
