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
public class Member {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String username;

    private Member(String username) {
        this.username = username;
    }

    public static Member of(String username) {
        return new Member(username);
    }
}