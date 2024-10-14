package study.spring_proxy.pureproxy.concreateproxy.code;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConcreateClient {

    private final ConcreateLogic logic;

    public void execute() {
        logic.operation();
    }
}
