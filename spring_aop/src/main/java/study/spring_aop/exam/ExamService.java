package study.spring_aop.exam;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.spring_aop.exam.annotation.Retry;
import study.spring_aop.exam.annotation.Trace;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;

    @Trace
    public String request(String itemId) {
        return examRepository.save(itemId);
    }
}
