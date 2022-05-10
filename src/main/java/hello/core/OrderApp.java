package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        /* 의존관계 주입을 위한 객체 생성 */
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        /* 회원가입 */
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        /* 상품주문 */
        Order order = orderService.createOrder(memberId, "itemA", 20000);

        /* 결과출력 */
        System.out.println("order = " + order); //Order 객체의 toString() 메서드 출력
    }
}
