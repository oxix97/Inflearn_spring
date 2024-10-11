package study.spring_proxy.pureproxy.decorator.code.v2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Component implements IComponent {

    @Override
    public String operation() {
        log.info("Component operation()");
        return "data";
    }
}
