package study.demo.config;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.demo.item.application.service.ItemService;
import study.demo.item.application.service.ItemService_V1;
import study.demo.item.domain.repository.ItemRepository_V1;
import study.demo.item.infrastructure.jpa.JpaItemRepository_V1;

@Configuration
@RequiredArgsConstructor
public class JpaConfig {
    private final EntityManager em;

    @Bean
    public ItemService itemService() {
        return new ItemService_V1(itemRepository());
    }

    @Bean
    public ItemRepository_V1 itemRepository() {
        return new JpaItemRepository_V1(em);
    }
}
