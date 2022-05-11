package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component //컴포넌트 스캔
public class MemoryMemberRepository implements MemberRepository {

    /* 메모리에 DB 생성 */
    private static Map<Long, Member> store = new HashMap<>();

    /* repository 구현체 */
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
