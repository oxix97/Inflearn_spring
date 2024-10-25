package hello.boot.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void memberTest() {
        Member member = new Member("id1", "name1");
        memberRepository.save(member);
        Member findMember = memberRepository.find("id1");
        assertEquals(member, findMember);
    }
}