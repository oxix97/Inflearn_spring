package study.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

// MEMO : .yml 파일에다가 하는게 설정하는게 합리적이긴함. 커스텀 크게 안할거면
@Slf4j
@TestConfiguration
public class InMemoryDBConfig {
    @Bean
    public DataSource dataSource() {
        log.info("embedded datasource generated");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1;MODE=MySQL");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }
}
