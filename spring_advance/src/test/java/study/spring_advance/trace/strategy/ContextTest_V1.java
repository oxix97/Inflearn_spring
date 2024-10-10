package study.spring_advance.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import study.spring_advance.trace.strategy.code.Context_V1;
import study.spring_advance.trace.strategy.code.Strategy;
import study.spring_advance.trace.strategy.code.StrategyLogic1;
import study.spring_advance.trace.strategy.code.StrategyLogic2;

@Slf4j
public class ContextTest_V1 {

    @Test
    void strategy_V1() {
        Strategy logic1 = new StrategyLogic1();
        Context_V1 context1 = new Context_V1(logic1);
        context1.execute();

        Strategy logic2 = new StrategyLogic2();
        Context_V1 context2 = new Context_V1(logic2);
        context2.execute();
    }

    @Test
    void strategy_V2() {
        Context_V1 context1 = new Context_V1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });
        context1.execute();

        Context_V1 context2 = new Context_V1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
        context2.execute();
    }

    @Test
    void strategy_V3() {
        new Context_V1(() -> log.info("비즈니스 로직1 실행")).execute();
        new Context_V1(() -> log.info("비즈니스 로직2 실행")).execute();
    }
}
