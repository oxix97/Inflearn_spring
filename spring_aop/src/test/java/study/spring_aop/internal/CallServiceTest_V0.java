package study.spring_aop.internal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import study.spring_aop.internal.aop.CallLogAspect;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceTest_V0 {

    @Autowired
    CallService_V0 callServiceV0;

    @Test
    void external() {
        callServiceV0.external();
    }

    @Test
    void internal() {
        callServiceV0.internal();
    }
}
