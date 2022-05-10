package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    /* 서비스는 저장소 연결 */
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    /* 할인정책과도 연결 */
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    /* 주문 기능 구현체 */
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
