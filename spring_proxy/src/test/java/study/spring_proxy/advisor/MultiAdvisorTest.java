package study.spring_proxy.advisor;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import study.spring_proxy.cglib.code.TimeMethodInterceptor;
import study.spring_proxy.common.service.ServiceImpl;
import study.spring_proxy.common.service.ServiceInterface;

public class MultiAdvisorTest {

    @Test
    void multiAdvisorTest() {
        // MEMO : client -> proxy2 -> proxy1 -> target

        // proxy1
        ServiceImpl target = new ServiceImpl();
        ProxyFactory proxyFactory1 = new ProxyFactory(target);
        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
        proxyFactory1.addAdvisor(advisor1);
        ServiceInterface proxy1 = (ServiceInterface) proxyFactory1.getProxy();

        // proxy2 -> proxy1
        ProxyFactory proxyFactory2 = new ProxyFactory(proxy1);
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());
        proxyFactory2.addAdvisor(advisor2);
        ServiceInterface proxy2 = (ServiceInterface) proxyFactory2.getProxy();

        proxy2.save();
    }

    @Test
    void multiAdvisorTest_V2() {
        // MEMO : client -> proxy -> advisor2 -> advisor1 -> target

        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());

        ServiceImpl target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);

        proxyFactory.addAdvisor(advisor2);
        proxyFactory.addAdvisor(advisor1);

        ServiceInterface proxy2 = (ServiceInterface) proxyFactory.getProxy();

        proxy2.save();
    }

    @Slf4j
    static class Advice1 implements MethodInterceptor{
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice 1 invoke()");
            return invocation.proceed();
        }
    }

    @Slf4j
    static class Advice2 implements MethodInterceptor{
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice 2 invoke()");
            return invocation.proceed();
        }
    }
}
