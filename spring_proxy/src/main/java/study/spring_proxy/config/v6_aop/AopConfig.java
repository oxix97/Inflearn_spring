package study.spring_proxy.config.v6_aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import study.spring_proxy.config.AppConfig_V1;
import study.spring_proxy.config.AppConfig_V2;
import study.spring_proxy.config.v6_aop.aspect.LogTraceAspect;
import study.spring_proxy.trace.logtrace.LogTrace;

@Configuration
@Import({AppConfig_V2.class, AppConfig_V1.class})
public class AopConfig {

    @Bean
    public LogTraceAspect logTraceAspect(LogTrace trace) {
        return new LogTraceAspect(trace);
    }
}
