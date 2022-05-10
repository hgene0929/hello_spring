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
        /* 클라이언트는 서비스와 연결 */
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        /* 회원가입 */
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        /* 상품주문 */
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        /* 결과출력 */
        System.out.println("order = " + order); //Order 객체의 toString() 메서드 출력
    }
}
