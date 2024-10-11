package study.spring_proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import study.spring_proxy.config.AppConfig_V1;

@Import(AppConfig_V1.class)
@SpringBootApplication(scanBasePackages = "study.spring_proxy.aop")
public class SpringProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringProxyApplication.class, args);
    }
}
