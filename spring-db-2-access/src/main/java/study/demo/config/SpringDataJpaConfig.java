package study.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.demo.item.application.service.ItemService;
import study.demo.item.application.service.ItemService_V1;
import study.demo.item.domain.repository.ItemRepository_V1;
import study.demo.item.infrastructure.jpa.JpaItemRepository_V2;
import study.demo.item.infrastructure.jpa.SpringDataJpaItemRepository;

@Configuration
@RequiredArgsConstructor
public class SpringDataJpaConfig {
    private final SpringDataJpaItemRepository repository;

    @Bean
    public ItemService itemService() {
        return new ItemService_V1(itemRepository());
    }

    @Bean
    public ItemRepository_V1 itemRepository() {
        return new JpaItemRepository_V2(repository);
    }
}
