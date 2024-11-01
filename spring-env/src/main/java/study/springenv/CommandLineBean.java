package study.springenv;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CommandLineBean {

    private final ApplicationArguments arguments;

    @PostConstruct
    public void init() {
        log.info("SourceArgs: {}", arguments.getSourceArgs());
        log.info("options name : {}",arguments.getOptionNames());

        arguments.getOptionNames().forEach(name -> {
            log.info("option args {}={}", name, arguments.getOptionValues(name));
        });
    }
}
