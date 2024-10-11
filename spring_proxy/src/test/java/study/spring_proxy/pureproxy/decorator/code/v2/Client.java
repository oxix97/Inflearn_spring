package study.spring_proxy.pureproxy.decorator.code.v2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class Client {

    private final IComponent component;

    public void execute() {
        String result = component.operation();
        log.info("result = {}", result);
    }
}
