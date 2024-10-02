package study.demo.item.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.demo.item.domain.dto.ItemSearchCond;
import study.demo.item.domain.dto.ItemUpdateDto;
import study.demo.item.domain.entity.Item;
import study.demo.item.domain.repository.ItemRepository_V1;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ItemService_V1 implements ItemService {
    private final ItemRepository_V1 itemRepositoryV1;

    @Override
    public Item save(Item item) {
        return itemRepositoryV1.save(item);
    }

    @Override
    public void update(Long itemId, ItemUpdateDto dto) {
        itemRepositoryV1.update(itemId, dto);
    }

    @Override
    public Optional<Item> findItem(Long itemId) {
        return itemRepositoryV1.findById(itemId);
    }

    @Override
    public List<Item> findItems(ItemSearchCond cond) {
        return itemRepositoryV1.findAll(cond);
    }

    @Override
    public void delete(Long itemId) {
        itemRepositoryV1.deleteById(itemId);
    }
}
