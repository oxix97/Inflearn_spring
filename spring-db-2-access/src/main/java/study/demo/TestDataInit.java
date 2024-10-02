package study.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import study.demo.item.domain.entity.Item;
import study.demo.item.domain.repository.ItemRepository_V1;

@Slf4j
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemRepository_V1 itemRepositoryV1;

    /**
     * 확인용 초기 데이터 추가
     */
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        log.info("test data init");
        itemRepositoryV1.save(new Item("itemA", 10000, 10));
        itemRepositoryV1.save(new Item("itemB", 20000, 20));
    }

}