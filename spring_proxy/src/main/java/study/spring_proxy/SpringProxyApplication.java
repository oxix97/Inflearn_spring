package study.spring_proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import study.spring_proxy.config.v2_dynamicproxy.DynamicProxyFilterConfig;
import study.spring_proxy.config.v3_proxyfactory.ProxyFactoryConfig_V1;
import study.spring_proxy.config.v3_proxyfactory.ProxyFactoryConfig_V2;
import study.spring_proxy.config.v4_postprocessor.postprocessor.BeanPostProcessorConfig;
import study.spring_proxy.config.v4_postprocessor.postprocessor.PackageLogTracePostProcessor;
import study.spring_proxy.config.v5_autoproxy.AutoProxyConfig;
import study.spring_proxy.trace.logtrace.LogTrace;
import study.spring_proxy.trace.logtrace.ThreadLocalLogTrace;

//@Import({AppConfig_V1.class, AppConfig_V2.class})
//@Import(InterfaceProxyConfig.class)
//@Import(ConcreateProxyConfig.class)
//@Import(DynamicProxyBasicConfig.class)
//@Import(DynamicProxyFilterConfig.class)
//@Import(ProxyFactoryConfig_V1.class)
//@Import(ProxyFactoryConfig_V2.class)
//@Import(BeanPostProcessorConfig.class)
@Import(AutoProxyConfig.class)
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
