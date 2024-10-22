package study.spring_aop;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import study.spring_aop.order.OrderRepository;
import study.spring_aop.order.OrderService;
import study.spring_aop.order.aop.Aspect_V1;

import static org.junit.jupiter.api.Assertions.assertThrows;


@Slf4j
@Import(Aspect_V1.class)
@SpringBootTest
public class AopTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void aopInfo() {
        log.info("isAopProxy={}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy={}", AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void success() {
        orderService.orderItem("itemA");
    }

    @Test
    void exception() {
        assertThrows(IllegalStateException.class, () -> orderService.orderItem("ex"));
    }
}
