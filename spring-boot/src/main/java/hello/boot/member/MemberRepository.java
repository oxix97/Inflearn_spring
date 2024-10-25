package hello.boot.member;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final JdbcTemplate template;

    @PostConstruct
    private void init() {
        template.execute("create table member(member_id varchar primary key, name varchar)");
    }

    public void save(Member member) {
        template.update("insert into member(member_id, name) values(?, ?)", member.getMemberId(), member.getName());
    }

    public Member find(String memberId) {
        return template.queryForObject(
                "select * from member where member_id = ?",
                BeanPropertyRowMapper.newInstance(Member.class),
                memberId
        );
    }

    public List<Member> findAll() {
        return template.query(
                "select * from member",
                BeanPropertyRowMapper.newInstance(Member.class)
        );
    }

}
