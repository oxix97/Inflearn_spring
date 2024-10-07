package study.spring_advance.aop.v0;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService_V0 {

    private final OrderRepository_V0 orderRepository;

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
