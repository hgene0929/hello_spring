package hello.core.member;

public class MemberServiceImpl implements MemberService {
    //회원 레포지토리 선언( 사용할 DB 선언 )
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
