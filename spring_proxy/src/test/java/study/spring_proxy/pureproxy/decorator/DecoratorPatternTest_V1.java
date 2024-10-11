package study.spring_proxy.pureproxy.decorator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import study.spring_proxy.pureproxy.decorator.code.v1.*;

@Slf4j
public class DecoratorPatternTest_V1 {

    @Test
    void noDecoratorTest() {
        Component realComponent = new RealComponent();
        new DecoratorPatternClient(realComponent).execute();
    }

    // MEMO : Client -> MessageDecorator -> RealComponent
    @Test
    void messageDecoratedTest() {
        Component realComponent = new RealComponent();
        Component messageDecorator = new MessageDecorator(realComponent);
        new DecoratorPatternClient(messageDecorator).execute();
    }

    // MEMO : Client -> TimeDecorator -> MessageDecorator -> RealComponent
    @Test
    void timeDecoratedTest() {
        Component realComponent = new RealComponent();
        Component messageDecorator = new MessageDecorator(realComponent);
        Component timeDecorator = new TimeDecorator(messageDecorator);
        new DecoratorPatternClient(timeDecorator).execute();
    }
}
