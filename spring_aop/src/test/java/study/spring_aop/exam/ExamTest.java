package study.spring_aop.exam;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import study.spring_aop.exam.aop.RetryAspect;
import study.spring_aop.exam.aop.TraceAspect;

@Slf4j
@Import({TraceAspect.class, RetryAspect.class})
@SpringBootTest
class ExamTest {

    @Autowired
    ExamService examService;

    @Test
    void test() {
        for (int i = 1; i <= 10; i++) {
            log.info("client request : i = {}", i);
            examService.request("data" + i);
        }
    }
}