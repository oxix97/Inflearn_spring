package study.spring_aop.internal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallService_V1 {

    // MEMO : 생성자를 만들면 순환참조 에러 발생
    private CallService_V1 callServiceV1;

    @Autowired
    public void setCallServiceV1(CallService_V1 callServiceV1) {
        log.info("callServiceV1 setter = {}", callServiceV1.getClass());
        this.callServiceV1 = callServiceV1;
    }

    public void external() {
        log.info("call external");
        callServiceV1.internal();
    }

    public void internal() {
        log.info("call internal");
    }

}
