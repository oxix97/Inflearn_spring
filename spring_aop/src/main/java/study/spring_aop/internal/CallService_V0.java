package study.spring_aop.internal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallService_V0 {

    public void external() {
        log.info("call external");
        internal(); // this.internal()
    }

    public void internal() {
        log.info("call internal");
    }

}
