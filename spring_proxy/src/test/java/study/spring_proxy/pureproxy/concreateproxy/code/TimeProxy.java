package study.spring_proxy.pureproxy.concreateproxy.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TimeProxy extends ConcreateLogic{

    private final ConcreateLogic logic;

    @Override
    public String operation() {
        log.info("TimeProxy operation()");
        long start = System.currentTimeMillis();

        String result = logic.operation();

        long end = System.currentTimeMillis();
        long time = end - start;
        log.info("TimeProxy time = {}", time);
        return result;
    }
}
