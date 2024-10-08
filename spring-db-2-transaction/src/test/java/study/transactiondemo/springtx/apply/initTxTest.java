package study.transactiondemo.springtx.apply;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@SpringBootTest
public class initTxTest {

    @Test
    void go() {

    }

    @TestConfiguration
    static class Config {
        @Bean
        public Hello hello() {
            return new Hello();
        }
    }

    @Slf4j
    static class Hello {
        @PostConstruct
        @Transactional
        public void init_V1() {
            boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.warn("Hello init @PostConstruct isActive={}", isActive);
        }

        @EventListener(ApplicationReadyEvent.class)
        @Transactional
        public void init_V2() {
            boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.warn("Hello init @EventListener isActive={}", isActive);
        }
    }
}
