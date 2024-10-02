package study.demo.item.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.demo.item.domain.dto.ItemSearchCond;
import study.demo.item.domain.dto.ItemUpdateDto;
import study.demo.item.domain.entity.Item;
import study.demo.item.domain.repository.ItemRepository_V2;
import study.demo.item.infrastructure.jpa.ItemQueryRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ItemService_V2 implements ItemService {
    private final ItemRepository_V2 itemRepository;
    private final ItemQueryRepository itemQueryRepository;

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void update(Long itemId, ItemUpdateDto dto) {
        Item findItem = itemRepository.findById(itemId).orElseThrow();
        findItem.setItemName(dto.itemName());
        findItem.setPrice(dto.price());
        findItem.setQuantity(dto.quantity());
    }

    @Override
    public Optional<Item> findItem(Long itemId) {
        return itemRepository.findById(itemId);
    }

    @Override
    public List<Item> findItems(ItemSearchCond cond) {
        return itemQueryRepository.findAll(cond);
    }

    @Override
    public void delete(Long itemId) {
        itemRepository.deleteById(itemId);
    }
}
