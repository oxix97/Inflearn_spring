package study.demo.item.infrastructure.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import study.demo.item.domain.dto.ItemSearchCond;
import study.demo.item.domain.dto.ItemUpdateDto;
import study.demo.item.domain.entity.Item;
import study.demo.item.domain.repository.ItemRepository_V1;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
public class JpaItemRepository_V2 implements ItemRepository_V1 {

    private final SpringDataJpaItemRepository repository;

    @Override
    public Item save(Item item) {
        return repository.save(item);
    }

    @Override
    public Optional<Item> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void update(Long id, ItemUpdateDto dto) {
        Item findItem = repository.findById(id).orElseThrow();
        findItem.setItemName(dto.itemName());
        findItem.setPrice(dto.price());
        findItem.setQuantity(dto.quantity());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

//    @Override
//    public List<Item> findAll(ItemSearchCond cond) {
//        return repository.findAll().stream()
//                .filter(it -> itemNameCheck(it, cond))
//                .filter(it -> itemPriceCheck(it, cond))
//                .toList();
//    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        String itemName = cond.itemName();
        Integer maxPrice = cond.maxPrice();

        if (StringUtils.hasText(itemName) && maxPrice != null)
            return repository.findItems("%" + itemName + "%", maxPrice);

        if (StringUtils.hasText(itemName))
            return repository.findByItemNameLike("%" + itemName + "%");

        if (maxPrice != null)
            return repository.findByPriceLessThanEqual(maxPrice);

        return repository.findAll();
    }

    private boolean itemNameCheck(Item item, ItemSearchCond cond) {
        if (cond.itemName() == null) return true;
        return item.getItemName().contains(cond.itemName());
    }

    private boolean itemPriceCheck(Item item, ItemSearchCond cond) {
        if (cond.maxPrice() == null) return true;
        return item.getPrice() <= cond.maxPrice();
    }
}
