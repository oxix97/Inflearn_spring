package study.spring_advance.aop.v0;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v0/orders")
@RestController
public class OrderController_V0 {

    private final OrderService_V0 orderService;

    @GetMapping("/{itemId}")
    public String order(@PathVariable String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }
}
