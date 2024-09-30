package study.transactiondemo.springtx.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class RollbackTest {

    @Autowired
    RollbackService rollbackService;

    @Test
    void runtimeException() {
        assertThrows(RuntimeException.class, rollbackService::runtimeException);
    }

    @Test
    void checkedException() {
        assertThrows(MyException.class, rollbackService::checkedException);
    }

    @Test
    void checkedExceptionWithRollbackFor() {
        assertThrows(MyException.class, rollbackService::checkedExceptionWithRollbackFor);
    }

    @TestConfiguration
    static class RollbackTestConfig {
        @Bean
        RollbackService rollbackService() {
            return new RollbackService();
        }
    }

    @Slf4j
    static class RollbackService {
        // MEMO : 런타임 예외 발생 : 롤백
        @Transactional
        public void runtimeException() {
            log.error("런타임 예외 발생 : 롤백");
            throw new RuntimeException();
        }

        // MEMO : 체크 예외 발생 : 커밋
        @Transactional
        public void checkedException() throws MyException {
            log.error("체크 예외 발생 : 커밋");
            throw new MyException();
        }

        // MEMO : 체크 예외 발생 : rollbackFor 지정 : 롤백
        @Transactional(rollbackFor = MyException.class)
        public void checkedExceptionWithRollbackFor() throws MyException {
            log.error("체크 예외 발생 : 롤백");
            throw new MyException();
        }
    }

    static class MyException extends Exception {

    }
}
