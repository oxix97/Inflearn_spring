package study.spring_proxy.pureproxy.decorator.code.v2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator extends Decorator {

    public TimeDecorator(IComponent component) {
        super(component);
    }

    @Override
    public String operation() {
        log.info("TimeDecorator 실행");
        long startTime = System.currentTimeMillis();

        String result = super.operation();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeDecorator 종료 resultTime = {}", resultTime);

        return result;
    }
}
