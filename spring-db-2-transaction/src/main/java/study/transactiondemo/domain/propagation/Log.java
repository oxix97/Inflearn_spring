package study.transactiondemo.domain.propagation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Log {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String message;

    private Log(String message) {
        this.message = message;
    }

    public static Log of(String message) {
        return new Log(message);
    }
}
