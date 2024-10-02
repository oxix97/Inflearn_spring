package study.transactiondemo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.transactiondemo.domain.order.NotEnoughMoneyException;
import study.transactiondemo.domain.order.Order;
import study.transactiondemo.repository.OrderRepository;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public void order(Order order) throws NotEnoughMoneyException {
        log.info("OrderService.order() : {}", order);
        orderRepository.save(order);

        log.info("결제 프로세스 진입"); // 예외 상황 재연
        if (order.getUsername().equals("예외")) {
            throw new RuntimeException("시스템 예외");
        }else if(order.getUsername().equals("잔고부족")) {
            order.setPayStatus("대기");
            throw new NotEnoughMoneyException("잔고가 부족합니다.");
        }else{
            log.info("정상 승인");
            order.setPayStatus("완료");
        }
        log.info("결제 프로세스 완료");
    }
}
