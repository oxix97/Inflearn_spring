package study.demo.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.demo.item.application.service.ItemService;
import study.demo.item.application.service.ItemService_V2;
import study.demo.item.domain.dto.ItemSearchCond;
import study.demo.item.domain.dto.ItemUpdateDto;
import study.demo.item.domain.entity.Item;
import study.demo.item.domain.repository.ItemRepository_V2;
import study.demo.item.infrastructure.jpa.ItemQueryRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@Slf4j
@Transactional
@SpringBootTest
public class ItemServiceTest {
    @Autowired
    ItemRepository_V2 itemRepository;
    @Autowired
    ItemQueryRepository itemQueryRepository;
    @Autowired
    ItemService itemService;

    @Test
    void save() {
        // Given
        Item item = new Item("item1", 10000, 10);

        // When
        Item savedItem = itemService.save(item);

        // Then
        Item findItem = itemRepository.findById(savedItem.getId()).get();
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void updateItem() {
        // Given
        Item item = new Item("item1", 10000, 10);
        Item savedItem = itemService.save(item);
        Long itemId = savedItem.getId();

        // When
        itemService.update(itemId, new ItemUpdateDto("item2", 20000, 30));

        // Then
        Item updatedItem = itemRepository.findById(itemId).get();
        assertThat(updatedItem.getItemName()).isEqualTo("item2");
        assertThat(updatedItem.getPrice()).isEqualTo(20000);
        assertThat(updatedItem.getQuantity()).isEqualTo(30);
    }

    @Test
    void findItems() {
        // Given
        Item item1 = new Item("itemA-1", 10000, 10);
        Item item2 = new Item("itemA-2", 20000, 20);
        Item item3 = new Item("itemB-1", 30000, 30);
        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);

        // When
        ItemSearchCond cond = new ItemSearchCond(null, 20000);
        List<Item> expected = itemService.findItems(cond);

        // Then
        List<Item> result = itemQueryRepository.findAll(cond);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void findItem() {
        // Given
        Item item = new Item("itemA-1", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        // When
        Optional<Item> findItem = itemService.findItem(itemId);

        // Then
        assertThat(findItem).isEqualTo(Optional.of(savedItem));
    }
}
