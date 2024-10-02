package study.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.demo.item.application.service.ItemService;
import study.demo.item.application.service.ItemService_V1;
import study.demo.item.infrastructure.memory.MemoryItemRepository;
import study.demo.item.domain.repository.ItemRepository_V1;

@Configuration
public class MemoryConfig {

    @Bean
    public ItemService itemService() {
        return new ItemService_V1(itemRepository());
    }

    @Bean
    public ItemRepository_V1 itemRepository() {
        return new MemoryItemRepository();
    }
}
