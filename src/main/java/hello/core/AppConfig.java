package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    /* 클라이언트 코드에서 사용할 역할에 대한 구현체 지정 */
    public MemberService memberService() {

        return new MemberServiceImpl(memberRepository());
    }

    /* 리팩토링 : 객체 생성의 중복 제거를 위해 생성 메서드 따로 추출 */
    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), dicountPolicy());
    }

    /* 리팩토링 : 구현체 변경이 용이하도록 생성 메서드 따로 추출 */
    private DiscountPolicy dicountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}