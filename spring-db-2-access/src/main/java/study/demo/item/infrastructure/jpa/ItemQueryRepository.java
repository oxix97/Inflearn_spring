package study.demo.item.infrastructure.jpa;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import study.demo.item.domain.dto.ItemSearchCond;
import study.demo.item.domain.entity.Item;
import study.demo.item.domain.entity.QItem;

import java.util.List;

import static study.demo.item.domain.entity.QItem.item;

@Slf4j
@Repository
public class ItemQueryRepository {

    private final JPAQueryFactory query;

    public ItemQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

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
