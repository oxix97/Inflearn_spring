package study.spring_advance.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import study.spring_advance.trace.strategy.code.*;

@Slf4j
public class ContextTest_V2 {

    @Test
    void strategy_V1() {
        Context_V2 context = new Context_V2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

   @Test
    void strategy_V2() {
        Context_V2 context = new Context_V2();

        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
    }

    @Test
    void strategy_V3() {
        Context_V2 context = new Context_V2();
        context.execute(() -> log.info("비즈니스 로직1 실행"));
        context.execute(() -> log.info("비즈니스 로직2 실행"));
    }
}
