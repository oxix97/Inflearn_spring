package study.spring_aop.proxyvs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import study.spring_aop.member.MemberService;
import study.spring_aop.member.MemberServiceImpl;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        factory.setProxyTargetClass(false); // JDK Proxy

        MemberService memberServiceProxy = (MemberService) factory.getProxy();

        // MEMO : 인터페이스를 구현한거지 구체 타입으로는 캐스팅 불가 -> 실패
        assertThrows(ClassCastException.class, () -> ((MemberServiceImpl) memberServiceProxy).hello("hello"));
    }

    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        factory.setProxyTargetClass(true); // CGLIB Proxy

        //프록시를 인터페이스로 캐스팅 성공
        MemberService memberServiceProxy = (MemberService) factory.getProxy();

        //구체클래스를 기반으로 프록시를 생성하여 캐스팅 성공
        MemberServiceImpl memberServiceImplProxy = (MemberServiceImpl) memberServiceProxy;

    }

}
