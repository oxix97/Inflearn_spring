package study.spring_proxy.pureproxy.decorator.code.v2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator extends Decorator {

    public MessageDecorator(IComponent component) {
        super(component);
    }

    @Override
    public String operation() {
        log.info("MessageDecorator operation()");
        String result = super.operation();
        String decoResult = "*****" + result + "*****";
        log.info("decoResult = {}", decoResult);
        return decoResult;
    }
}
