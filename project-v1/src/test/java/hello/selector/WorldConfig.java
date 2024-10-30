package hello.selector;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class WorldConfig {

    @Bean
    public WorldBean worldBean() {
        return new WorldBean();
    }
}
