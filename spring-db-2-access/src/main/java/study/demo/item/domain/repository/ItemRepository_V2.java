package study.demo.item.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.demo.item.domain.entity.Item;

public interface ItemRepository_V2 extends JpaRepository<Item,Long> {
}
