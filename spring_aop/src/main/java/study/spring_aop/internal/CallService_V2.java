package study.spring_aop.internal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallService_V2 {

    private final ObjectProvider<CallService_V2> serviceProvider;


    public CallService_V2(ObjectProvider<CallService_V2> serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public void external() {
        log.info("call external");
        CallService_V2 callServiceV2 = serviceProvider.getObject();
        callServiceV2.internal();
    }

    public void internal() {
        log.info("call internal");
    }

}
