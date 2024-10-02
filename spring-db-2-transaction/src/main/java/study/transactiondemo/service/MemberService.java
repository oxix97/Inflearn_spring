package study.transactiondemo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.transactiondemo.domain.propagation.Log;
import study.transactiondemo.domain.propagation.Member;
import study.transactiondemo.repository.LogRepository;
import study.transactiondemo.repository.MemberRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final LogRepository logRepository;

    @Transactional
    public void join_V1(String username) {
        Member member = Member.of(username);

        log.info("=======memberRepository 호출 시작=======");
        memberRepository.save(member);
        log.info("=======memberRepository 호출 종료=======");

        log.info("=======logRepository 호출 시작=======");
        logRepository.save(username);
        log.info("=======logRepository 호출 종료=======");
    }

    @Transactional
    public void join_V2(String username) {
        Member member = Member.of(username);

        log.info("=======memberRepository 호출 시작=======");
        memberRepository.save(member);
        log.info("=======memberRepository 호출 종료=======");

        log.info("=======logRepository 호출 시작=======");
        try {
            logRepository.save(username);
        } catch (RuntimeException e) {
            log.info("로그 저장 실패 : {}", e.getMessage());
            log.info("정상 흐름 반환");
        }

        log.info("=======logRepository 호출 종료=======");
    }
}
