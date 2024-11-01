package study.springenv;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EnvironmentCheck {

    private final Environment env;

    // MEMO : 서로 다른 시스템의 속성 모두 Environment 를 통해 동일한 방법으로 접근이 가능
    // MEMO : 중복된 경우 Spring Boot 환경에서 설정된 우선 순위의 환경 변수 도출
    @PostConstruct
    public void init() {
        String url = env.getProperty("url");
        String username = env.getProperty("my_username");
        String password = env.getProperty("password");

        log.info("env url : {}", url);
        log.info("env username : {}", username);
        log.info("env password : {}", password);
    }
}
