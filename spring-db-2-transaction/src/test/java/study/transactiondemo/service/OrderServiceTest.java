package study.transactiondemo.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.transactiondemo.domain.order.NotEnoughMoneyException;
import study.transactiondemo.domain.order.Order;
import study.transactiondemo.repository.OrderRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    @DisplayName("[완료] 주문")
    void order_complete() throws NotEnoughMoneyException {
        //given
        Order order = getOrder("홍길동");
        order.setPayStatus("정상");

        //when
        orderService.order(order);

        //then
        Order findOrder = orderRepository.findById(order.getId()).get();
        assertEquals("완료", findOrder.getPayStatus());
    }

    @Test
    @DisplayName("[예외] 시스템 예외, 롤백 O")
    void order_fail_RuntimeException() {
        //given
        Order order = getOrder("예외");
        //when
        assertThrows(RuntimeException.class, () -> orderService.order(order));

        //then
        Optional<Order> findOrder = orderRepository.findById(order.getId());
        assertEquals(Optional.empty(), findOrder);
    }

    @Test
    @DisplayName("[예외] 잔고 부족, 롤백 X")
    void order_fail_NotEnoughMoneyException() {
        //given
        Order order = getOrder("잔고부족");

        //when
        assertThrows(NotEnoughMoneyException.class, () -> orderService.order(order));

        //then
        Order findOrder = orderRepository.findById(order.getId()).get();
        assertEquals("대기", findOrder.getPayStatus());
    }

    private Order getOrder(String username) {
        return Order.of(username, "대기");
    }
}