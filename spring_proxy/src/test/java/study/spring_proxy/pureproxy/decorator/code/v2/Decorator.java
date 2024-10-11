package study.spring_proxy.pureproxy.decorator.code.v2;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class Decorator implements IComponent {

    private final IComponent component;

    @Override
    public String operation() {
        return component.operation();
    }
}
