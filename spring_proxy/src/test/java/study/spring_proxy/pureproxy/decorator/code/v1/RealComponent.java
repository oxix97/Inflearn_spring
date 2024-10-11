package study.spring_proxy.pureproxy.decorator.code.v1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealComponent implements Component {

    @Override
    public String operation() {
        log.info("Real Component");
        return "data";
    }
}
