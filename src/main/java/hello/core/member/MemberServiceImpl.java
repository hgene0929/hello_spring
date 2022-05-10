package hello.core.member;

public class MemberServiceImpl implements MemberService {
    /* 저장소 선언 : 서비스는 레포지토리와 연결 */
    private final MemberRepository memberRepository;

    /* 생성자 주입 :
    관심사의 분리에 따라 외부에서 생성된 생성자를 통해 의존관계를 주입받는다 (사용할 구현체를 받음) */
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
}
