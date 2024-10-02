package study.transactiondemo.domain.order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Table(name = "orders")
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Order {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String username;
    @Setter
    private String payStatus;

    private Order(String username, String payStatus) {
        this.username = username;
        this.payStatus = payStatus;
    }

    public static Order of(String username, String payStatus) {
        return new Order(username, payStatus);
    }

}
