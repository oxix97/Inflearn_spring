package study.spring_aop.member;

import org.springframework.stereotype.Service;
import study.spring_aop.member.annotation.ClassAop;
import study.spring_aop.member.annotation.MethodAop;

@ClassAop
@Service
public class MemberServiceImpl implements MemberService {

    @Override
    @MethodAop(value = "test value")
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }
}
