package study.spring_proxy.pureproxy.concreateproxy;

import org.junit.jupiter.api.Test;
import study.spring_proxy.pureproxy.concreateproxy.code.ConcreateClient;
import study.spring_proxy.pureproxy.concreateproxy.code.ConcreateLogic;
import study.spring_proxy.pureproxy.concreateproxy.code.TimeProxy;

public class ConcreateProxyTest {

    @Test
    void noProxy() {
        ConcreateLogic logic = new ConcreateLogic();
        ConcreateClient client = new ConcreateClient(logic);
        client.execute();
    }

    // MEMO : Client -> Proxy -> Logic
    @Test
    void addProxy() {
        ConcreateLogic logic = new ConcreateLogic();
        TimeProxy timeProxy = new TimeProxy(logic);
        ConcreateClient client = new ConcreateClient(timeProxy);
        client.execute();
    }
}
