package study.demo.item.infrastructure.jpa;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import study.demo.item.domain.dto.ItemSearchCond;
import study.demo.item.domain.dto.ItemUpdateDto;
import study.demo.item.domain.entity.Item;
import study.demo.item.domain.entity.QItem;
import study.demo.item.domain.repository.ItemRepository_V1;

import java.util.List;
import java.util.Optional;

import static study.demo.item.domain.entity.QItem.item;

@Slf4j
@Repository
@Transactional
public class JpaItemRepository_V3 implements ItemRepository_V1 {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaItemRepository_V3(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Item save(Item item) {
        em.persist(item);
        return item;
    }

    @Override
    public Optional<Item> findById(Long id) {
        Item findItem = em.find(Item.class, id);
        return Optional.ofNullable(findItem);
    }

    @Override
    public void update(Long id, ItemUpdateDto dto) {
        Item findItem = em.find(Item.class, id);
        findItem.setItemName(dto.itemName());
        findItem.setPrice(dto.price());
        findItem.setQuantity(dto.quantity());
    }

    @Override
    public void deleteById(Long id) {
        Item findItem = em.find(Item.class, id);
        em.remove(findItem);
    }

    public List<Item> findAllOld(ItemSearchCond cond) {
        String itemName = cond.itemName();
        Integer maxPrice = cond.maxPrice();

        QItem item = QItem.item;
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.hasText(itemName)) {
            builder.and(item.itemName.like("%" + itemName + "%"));
        }
        if (maxPrice != null) {
            builder.and(item.price.loe(maxPrice));
        }
        return query.select(item)
                .from(item)
                .where(builder)
                .fetch();
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        String itemName = cond.itemName();
        Integer maxPrice = cond.maxPrice();
        QItem item = QItem.item;
        return query
                .select(item)
                .from(item)
                .where(likeItemName(itemName), maxPrice(maxPrice))
                .fetch();
    }

    // MEMO : Expression의 경우 null 반환시 querydsl 조건에서 자동으로 제외
    private BooleanExpression likeItemName(String itemName) {
        if (StringUtils.hasText(itemName)) {
            return item.itemName.like("%" + itemName + "%");
        }
        return null;
    }

    private BooleanExpression maxPrice(Integer maxPrice) {
        if (maxPrice != null) {
            return item.price.loe(maxPrice);
        }
        return null;
    }
}
