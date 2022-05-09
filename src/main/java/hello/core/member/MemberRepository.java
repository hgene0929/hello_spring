package hello.core.member;

public interface MemberRepository {

    //회원가입
    void save(Member member);
    //회원조회
    Member findById(Long memberId);
}
