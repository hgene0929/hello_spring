package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    /* 서비스는 저장소 연결 */
    private final MemberRepository memberRepository;
    /* 할인정책과도 연결 */
    private final DiscountPolicy discountPolicy;

    /* 생성자 주입 :
    관심사의 분리에 따라 외부에서 생성된 생성자를 통해 의존관계를 주입받는다 (사용할 구현체를 받음) */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /* 주문 기능 구현체 */
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    /* 싱글톤 컨테이너 동작 테스트를 위한 코드 */
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
