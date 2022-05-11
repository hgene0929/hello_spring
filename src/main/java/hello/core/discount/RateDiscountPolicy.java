package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component //컴포넌트 스캔
@Primary //의존관계 주입시, 동일한 타입을 가진 빈 객체 중 우선순위를 가진 객체 설정
public class RateDiscountPolicy implements DiscountPolicy {

    /* 할인정책 구현 : 정율할인 (상품금액의 10%) */
    private int discountPercent = 10;

    /* 할인정책 구현체 */
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
