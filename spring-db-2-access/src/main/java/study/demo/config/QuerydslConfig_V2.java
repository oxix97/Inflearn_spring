package study.demo.config;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.demo.item.application.service.ItemService;
import study.demo.item.application.service.ItemService_V1;
import study.demo.item.application.service.ItemService_V2;
import study.demo.item.domain.repository.ItemRepository_V1;
import study.demo.item.domain.repository.ItemRepository_V2;
import study.demo.item.infrastructure.jpa.ItemQueryRepository;
import study.demo.item.infrastructure.jpa.JpaItemRepository_V2;
import study.demo.item.infrastructure.jpa.JpaItemRepository_V3;
import study.demo.item.infrastructure.jpa.SpringDataJpaItemRepository;

@Configuration
@RequiredArgsConstructor
public class QuerydslConfig_V2 {
    private final EntityManager em;
    private final ItemRepository_V2 itemRepository_V2;

    @Bean
    public ItemService itemService() {
        return new ItemService_V2(itemRepository_V2,itemQueryRepository());
    }

    @Bean
    public ItemQueryRepository itemQueryRepository() {
        return new ItemQueryRepository(em);
    }

    @Bean
    public ItemRepository_V1 itemRepository() {
        return new JpaItemRepository_V3(em);
    }
}
