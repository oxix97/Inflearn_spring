package study.demo.item.infrastructure.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import study.demo.item.domain.dto.ItemSearchCond;
import study.demo.item.domain.dto.ItemUpdateDto;
import study.demo.item.domain.entity.Item;
import study.demo.item.domain.repository.ItemRepository_V1;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Repository
public class MyBatisItemRepository implements ItemRepository_V1 {

    private final ItemMapper mapper;

    @Override
    public Item save(Item item) {
        mapper.save(item);
        return item;
    }

    @Override
    public Optional<Item> findById(Long id) {
        return mapper.findById(id);
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        return mapper.findAll(cond);
    }

    @Override
    public void update(Long id, ItemUpdateDto dto) {
        mapper.update(id, dto);
    }

    @Override
    public void deleteById(Long id) {
        mapper.deleteById(id);
    }
}
