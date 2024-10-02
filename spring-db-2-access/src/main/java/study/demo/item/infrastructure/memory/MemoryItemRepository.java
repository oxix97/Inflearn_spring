package study.demo.item.infrastructure.memory;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import study.demo.item.domain.dto.ItemSearchCond;
import study.demo.item.domain.dto.ItemUpdateDto;
import study.demo.item.domain.entity.Item;
import study.demo.item.domain.repository.ItemRepository_V1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryItemRepository implements ItemRepository_V1 {
    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    @Override
    public Optional<Item> findById(Long id) {
        return Optional.of(store.get(id));
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        return store.values().stream()
                .filter(item -> itemNameCheck(item, cond.itemName()))
                .filter(item -> itemPriceCheck(item, cond.maxPrice()))
                .toList();
    }

    @Override
    public void update(Long id, ItemUpdateDto dto) {
        Item item = findById(id).orElseThrow();
        item.setItemName(dto.itemName());
        item.setPrice(dto.price());
        item.setQuantity(dto.quantity());
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    private boolean itemNameCheck(Item item, String itemName) {
        if (ObjectUtils.isEmpty(itemName)) return true;
        return item.getItemName().contains(itemName);
    }

    private boolean itemPriceCheck(Item item, Integer maxPrice) {
        if (maxPrice == null) return true;
        return item.getPrice() <= maxPrice;
    }

    //Test Method
    public void clear() {
        store.clear();
    }
}
