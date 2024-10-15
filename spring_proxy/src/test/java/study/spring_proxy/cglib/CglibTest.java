package study.spring_proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;
import study.spring_proxy.cglib.code.TimeMethodInterceptor;
import study.spring_proxy.common.service.ConcreateService;

@Slf4j
public class CglibTest {

    @Test
    void cglib() {
        ConcreateService target = new ConcreateService();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreateService.class);
        enhancer.setCallback(new TimeMethodInterceptor(target));
        ConcreateService proxy = (ConcreateService) enhancer.create();

        log.info("targetClass = {}", target.getClass());
        log.info("proxyClass = {}", proxy.getClass());

        proxy.call();
    }
}
