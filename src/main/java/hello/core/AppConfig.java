package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /* 클라이언트 코드에서 사용할 역할에 대한 구현체 지정 */
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService"); //호출로그를 남겨 인스턴스 확인
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService"); //호출로그를 남겨 인스턴스 확인
        return new OrderServiceImpl(memberRepository(), dicountPolicy());
    }

    /* 리팩토링 : 객체 생성의 중복 제거를 위해 생성 메서드 따로 추출 */
    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository"); //호출로그를 남겨 인스턴스 확인
        return new MemoryMemberRepository();
    }

    /* 리팩토링 : 구현체 변경이 용이하도록 생성 메서드 따로 추출 */
    @Bean
    public DiscountPolicy dicountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}