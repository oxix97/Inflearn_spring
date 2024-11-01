package study.spring_proxy.pureproxy.proxy;

import org.junit.jupiter.api.Test;
import study.spring_proxy.pureproxy.proxy.code.CacheProxy;
import study.spring_proxy.pureproxy.proxy.code.ProxyPatternClient;
import study.spring_proxy.pureproxy.proxy.code.RealSubject;
import study.spring_proxy.pureproxy.proxy.code.Subject;

public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        Subject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.execute();
        client.execute();
        client.execute();
    }

    // MEMO : [접근 제어] Client 코드를 건들지 않고 접근 제어 및 캐싱을 적용함.
    @Test
    void cacheProxyTest() {
        Subject realSubject = new RealSubject();
        Subject proxySubject = new CacheProxy(realSubject);
        ProxyPatternClient client = new ProxyPatternClient(proxySubject);
        client.execute();
        client.execute();
        client.execute();
    }
}
