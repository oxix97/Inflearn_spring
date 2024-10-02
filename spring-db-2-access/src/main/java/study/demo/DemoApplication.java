package study.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import study.demo.config.*;
import study.demo.item.domain.repository.ItemRepository_V1;

@Slf4j
@Import(QuerydslConfig_V2.class)
@RequiredArgsConstructor
@SpringBootApplication(scanBasePackages = "study.demo.item.interfaces")
public class DemoApplication {

    private final ItemRepository_V1 itemRepositoryV1;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    @Profile("local")
    public TestDataInit testDataInit() {
        return new TestDataInit(itemRepositoryV1);
    }
}
