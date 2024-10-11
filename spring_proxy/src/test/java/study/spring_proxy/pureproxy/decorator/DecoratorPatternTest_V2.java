package study.spring_proxy.pureproxy.decorator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import study.spring_proxy.pureproxy.decorator.code.v2.*;

@Slf4j
public class DecoratorPatternTest_V2 {

    @Test
    void noDecoratorTest() {
        Component component = new Component();
        new Client(component).execute();
    }

    @Test
    void messageDecoratorTest() {
        IComponent component = new Component();
        Decorator messageDecorator = new MessageDecorator(component);
        new Client(messageDecorator).execute();
    }

    @Test
    void timeDecoratorTest() {
        IComponent component = new Component();
        Decorator timeDecorator = new TimeDecorator(component);
        new Client(timeDecorator).execute();
    }

    @Test
    void decoratorTest() {
        IComponent component = new Component();
        Decorator timeDecorator = new TimeDecorator(component);
        Decorator messageDecorator = new MessageDecorator(timeDecorator);
        new Client(messageDecorator).execute();
    }
}
