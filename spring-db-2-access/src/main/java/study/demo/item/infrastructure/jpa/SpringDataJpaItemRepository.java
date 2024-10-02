package study.demo.item.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.demo.item.domain.entity.Item;

import java.util.List;

public interface SpringDataJpaItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByItemNameLike(String itemName);

    List<Item> findByPriceLessThanEqual(Integer price);

    @Query("select i from Item i where i.itemName like :itemName and i.price <= :price")
    List<Item> findItems(String itemName, Integer price);
}
