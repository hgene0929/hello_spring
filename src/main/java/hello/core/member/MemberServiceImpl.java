package hello.core.member;

public class MemberServiceImpl implements MemberService {
    /* 저장소 선언 : 서비스는 레포지토리와 연결 */
    private final MemberRepository memberRepository = new MemoryMemberRepository();

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
