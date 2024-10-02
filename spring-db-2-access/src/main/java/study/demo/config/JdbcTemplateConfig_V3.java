package study.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.demo.item.application.service.ItemService;
import study.demo.item.application.service.ItemService_V1;
import study.demo.item.infrastructure.jdbctemplate.JdbcTemplateItemRepository_V3;
import study.demo.item.domain.repository.ItemRepository_V1;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class JdbcTemplateConfig_V3 {
    private final DataSource dataSource;

    @Bean
    public ItemService itemService() {
        return new ItemService_V1(itemRepository());
    }

    @Bean
    public ItemRepository_V1 itemRepository() {
        return new JdbcTemplateItemRepository_V3(dataSource);
    }

}
