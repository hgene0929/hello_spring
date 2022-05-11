package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy {

    /* 할인정책 : 정액할인 (1000원) */
    private int discountFixAmount = 1000;

    /* 할인 구현 */
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) { //Enum 타입은 ==으로 비교 가능
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
