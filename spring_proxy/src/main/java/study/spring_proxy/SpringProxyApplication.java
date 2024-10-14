package study.spring_proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import study.spring_proxy.config.AppConfig_V1;
import study.spring_proxy.config.AppConfig_V2;
import study.spring_proxy.config.v1_proixy.InterfaceProxyConfig;
import study.spring_proxy.trace.logtrace.LogTrace;
import study.spring_proxy.trace.logtrace.ThreadLocalLogTrace;

//@Import({AppConfig_V1.class, AppConfig_V2.class})
@Import(InterfaceProxyConfig.class)
@SpringBootApplication(scanBasePackages = "study.spring_proxy.aop.v3")
public class SpringProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringProxyApplication.class, args);
    }

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}
