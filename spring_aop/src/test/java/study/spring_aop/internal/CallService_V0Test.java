package study.spring_aop.internal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import study.spring_aop.internal.aop.CallLogAspect;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceTest {

    @Autowired
    CallService_V0 callService;

    @Test
    void external() {
        callService.external();
    }

    @Test
    void internal() {
        callService.internal();
    }
}
