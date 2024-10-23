package study.spring_aop.exam;

import org.springframework.stereotype.Repository;
import study.spring_aop.exam.annotation.Retry;
import study.spring_aop.exam.annotation.Trace;

@Repository
public class ExamRepository {

    private static int seq = 0;

    @Trace
    @Retry
    public String save(String itemId) {
        if (++seq % 5 == 0) {
            throw new IllegalStateException("예외 발생");
        }
        return "ok";
    }
}
