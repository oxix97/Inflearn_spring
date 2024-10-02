package study.demo.item.infrastructure.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import study.demo.item.domain.dto.ItemSearchCond;
import study.demo.item.domain.dto.ItemUpdateDto;
import study.demo.item.domain.entity.Item;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {

    void save(Item item);

    void update(
            @Param("id") Long id,
            @Param("updateParam") ItemUpdateDto updateParam
    );

    List<Item> findAll(ItemSearchCond itemSearch);

    Optional<Item> findById(Long id);

    void deleteById(Long id);
}
