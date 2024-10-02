package study.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.demo.item.application.service.ItemService;
import study.demo.item.application.service.ItemService_V1;
import study.demo.item.infrastructure.mybatis.ItemMapper;
import study.demo.item.infrastructure.mybatis.MyBatisItemRepository;
import study.demo.item.domain.repository.ItemRepository_V1;

@RequiredArgsConstructor
@Configuration
public class MyBatisConfig {
    private final ItemMapper itemMapper;

    @Bean
    public ItemService itemService() {
        return new ItemService_V1(itemRepository());
    }

    @Bean
    public ItemRepository_V1 itemRepository() {
        return new MyBatisItemRepository(itemMapper);
    }
}
