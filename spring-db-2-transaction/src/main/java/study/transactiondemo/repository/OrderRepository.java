package study.transactiondemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.transactiondemo.domain.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
