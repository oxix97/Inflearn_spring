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
class CallServiceTest_V3 {

    @Autowired
    CallService_V3 callServiceV3;

    @Test
    void external() {
        callServiceV3.external();
    }
}
