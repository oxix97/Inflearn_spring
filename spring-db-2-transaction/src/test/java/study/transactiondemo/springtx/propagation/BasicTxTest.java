package study.transactiondemo.springtx.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.transaction.TransactionDefinition.PROPAGATION_REQUIRES_NEW;

@SuppressWarnings("LoggingSimilarMessage")
@Slf4j
@SpringBootTest
public class BasicTxTest {

    @Autowired
    PlatformTransactionManager tm;

    @Test
    void commit() {
        log.info("트랜잭션 시작");
        TransactionStatus status = tm.getTransaction(new DefaultTransactionAttribute());

        log.info("트랜잭션 커밋 시작");
        tm.commit(status);

        log.info("트랜잭션 커밋 완료");
    }

    @Test
    void rollback() {
        log.info("트랜잭션 시작");
        TransactionStatus status = tm.getTransaction(new DefaultTransactionAttribute());

        log.info("트랜잭션 롤백 시작");
        tm.rollback(status);

        log.info("트랜잭션 롤백 완료");
    }

    @Test
    void double_commit() {
        log.info("트랜잭션1 시작");
        TransactionStatus status1 = tm.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션1 커밋");
        tm.commit(status1);

        log.info("트랜잭션2 시작");
        TransactionStatus status2 = tm.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션2 커밋");
        tm.commit(status2);
    }

    @Test
    void commit_rollback() {
        log.info("트랜잭션1 시작");
        TransactionStatus status1 = tm.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션1 커밋");
        tm.commit(status1);

        log.info("트랜잭션2 시작");
        TransactionStatus status2 = tm.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션2 롤백");
        tm.rollback(status2);
    }

    @Test
    void inner_commit() {
        log.info("외부 트랜잭션 시작");
        TransactionStatus outer = tm.getTransaction(new DefaultTransactionAttribute());
        log.info("outer.isNewTransaction()={}", outer.isNewTransaction());

        log.info("내부 시작");
        TransactionStatus inner = tm.getTransaction(new DefaultTransactionAttribute());
        log.info("inner.isNewTransaction()={}", inner.isNewTransaction());
        log.info("내부 트랜잭션 커밋");
        tm.commit(inner);

        log.info("외부 트랜잭션 커밋");
        tm.commit(outer);
    }

    @Test
    void outer_rollback() {
        log.info("외부 트랜잭션 시작");
        TransactionStatus outer = tm.getTransaction(new DefaultTransactionAttribute());

        log.info("내부 트랜잭션 시작");
        TransactionStatus inner = tm.getTransaction(new DefaultTransactionAttribute());
        log.info("내부 트랜잭션 커밋");
        tm.commit(inner);

        log.info("외부 트랜잭션 롤백");
        tm.rollback(outer);
    }

    @Test
    void inner_rollback() {
        log.info("외부 트랜잭션 시작");
        TransactionStatus outer = tm.getTransaction(new DefaultTransactionAttribute());

        log.info("내부 트랜잭션 시작");
        TransactionStatus inner = tm.getTransaction(new DefaultTransactionAttribute());
        log.info("내부 트랜잭션 롤백");
        tm.rollback(inner); //rollback-only 표시

        log.info("외부 트랜잭션 커밋");
        assertThrows(UnexpectedRollbackException.class, () -> tm.commit(outer));
    }

    @Test
    void inner_rollback_requires_new() {
        log.info("외부 트랜잭션 시작");
        TransactionStatus outer = tm.getTransaction(new DefaultTransactionAttribute());
        log.info("outer.isNewTransaction()={}", outer.isNewTransaction());

        innerLogin_RequiresNew();

        log.info("외부 트랜잭션 커밋");
        tm.commit(outer);
    }

    @Test
    void inner_commit_outer_rollback() {
        log.info("외부 트랜잭션 시작");
        TransactionStatus outer = tm.getTransaction(new DefaultTransactionAttribute());
        log.info("outer.isNewTransaction()={}", outer.isNewTransaction());

        log.info("내부 트랜잭션 시작");
        DefaultTransactionAttribute definition = new DefaultTransactionAttribute();
        definition.setPropagationBehavior(PROPAGATION_REQUIRES_NEW); // 신규 트랜잭션 생성
        TransactionStatus inner = tm.getTransaction(definition);
        log.info("inner.isNewTransaction()={}", inner.isNewTransaction());
        log.info("내부 트랜잭션 커밋");
        tm.commit(inner);

        log.info("외부 트랜잭션 롤백");
        tm.rollback(outer);
    }

    // MEMO : 트랜잭션을 분리하려면 connection을 분리해야함.
    private void innerLogin_RequiresNew() {
        log.info("내부 트랜잭션 시작");
        DefaultTransactionAttribute definition = new DefaultTransactionAttribute();
        definition.setPropagationBehavior(PROPAGATION_REQUIRES_NEW); // 신규 트랜잭션 생성
        TransactionStatus inner = tm.getTransaction(definition);
        log.info("inner.isNewTransaction()={}", inner.isNewTransaction());

        log.info("내부 트랜잭션 롤백");
        tm.rollback(inner);
    }

    @TestConfiguration
    static class Config {

        @Bean
        public PlatformTransactionManager transactionManager(DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }
    }
}
