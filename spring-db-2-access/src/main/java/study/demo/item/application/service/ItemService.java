package study.demo.item.application.service;

import study.demo.item.domain.dto.ItemSearchCond;
import study.demo.item.domain.dto.ItemUpdateDto;
import study.demo.item.domain.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Item save(Item item);

    void update(Long itemId, ItemUpdateDto dto);

    Optional<Item> findItem(Long itemId);

    List<Item> findItems(ItemSearchCond cond);

    void delete(Long itemId);
}
