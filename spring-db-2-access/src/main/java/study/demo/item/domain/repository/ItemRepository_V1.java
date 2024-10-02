package study.demo.item.domain.repository;

import study.demo.item.domain.entity.Item;
import study.demo.item.domain.dto.ItemSearchCond;
import study.demo.item.domain.dto.ItemUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ItemRepository_V1 {

    Item save(Item item);

    Optional<Item> findById(Long id);

    List<Item> findAll(ItemSearchCond cond);

    void update(Long id, ItemUpdateDto dto);

    void deleteById(Long id);
}
