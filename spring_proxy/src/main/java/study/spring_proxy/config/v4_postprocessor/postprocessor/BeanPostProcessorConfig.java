package study.spring_proxy.config.v4_postprocessor.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import study.spring_proxy.config.AppConfig_V1;
import study.spring_proxy.config.AppConfig_V2;
import study.spring_proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import study.spring_proxy.trace.logtrace.LogTrace;

@Slf4j
@Configuration
@Import({AppConfig_V1.class, AppConfig_V2.class})
public class BeanPostProcessorConfig {

    @Bean
    public PackageLogTracePostProcessor logTracePostProcessor(LogTrace trace) {
        return new PackageLogTracePostProcessor("study.spring_proxy.aop", getAdvisor(trace));
    }

    private Advisor getAdvisor(LogTrace trace) {
        //pointcut
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        //advice
        LogTraceAdvice advice = new LogTraceAdvice(trace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
