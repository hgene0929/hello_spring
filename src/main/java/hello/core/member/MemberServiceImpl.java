package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //컴포넌트 스캔
public class MemberServiceImpl implements MemberService {
    /* 저장소 선언 : 서비스는 레포지토리와 연결 */
    private final MemberRepository memberRepository;

    /* 생성자 주입 :
    관심사의 분리에 따라 외부에서 생성된 생성자를 통해 의존관계를 주입받는다 (사용할 구현체를 받음) */
    @Autowired //의존관계 자동주입
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /* 기능 및 비즈니스 로직 */
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    /* 싱글톤 컨테이너 동작 테스트를 위한 코드 */
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
