package hello.boot.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
public class DbConfigTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    TransactionManager tm;

    @Autowired
    JdbcTemplate template;

    @Test
    void checkBean() {
        log.info("dataSource: {}", dataSource);
        log.info("transactionManager: {}", tm);
        log.info("template: {}", template);

        assertNotNull(dataSource);
        assertNotNull(tm);
        assertNotNull(template);
    }

}
