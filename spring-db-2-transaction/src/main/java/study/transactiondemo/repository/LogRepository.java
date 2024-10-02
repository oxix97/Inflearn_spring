package study.transactiondemo.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import study.transactiondemo.domain.propagation.Log;

import java.util.Optional;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Slf4j
@RequiredArgsConstructor
@Repository
public class LogRepository {
    private final EntityManager em;

    @Transactional(propagation = REQUIRES_NEW)
    public void save(String message) {
        Log logMessage = Log.of(message);
        log.info("log 저장: {}", logMessage.getMessage());
        em.persist(logMessage);

        if (logMessage.getMessage().contains("로그예외")) {
            log.info("로그 저장시 예외 발생");
            throw new RuntimeException("로그 저장 예외 발생");
        }
    }

    public void saveN(String message) {
        Log logMessage = Log.of(message);
        log.info("log 저장: {}", logMessage.getMessage());
        em.persist(logMessage);

        if (logMessage.getMessage().contains("로그예외")) {
            log.info("로그 저장시 예외 발생");
            throw new RuntimeException("로그 저장 예외 발생");
        }
    }

    public Optional<Log> find(String message) {
        return em.createQuery("select l from Log l where l.message = :message", Log.class)
                .setParameter("message", message)
                .getResultList()
                .stream().findAny();
    }
}
