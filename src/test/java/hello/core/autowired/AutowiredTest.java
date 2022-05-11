package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
        /* 스프링 빈이 아닌 객체에 의존관계를 자동주입해도 정상작동하도록 하는 방법 */

        /* @Autowired(required=false) : 메서드 호출 자체를 X */
        @Autowired(required = false)
        public void setNoBean1(Member member) {
            System.out.println("setNoBean1 = "+member);
        }

        /* 의존관계를 주입하는 대상 객체에 @Nullable 처리 : 스프링 빈이 아닌 경우 null 호출 */
        @Autowired
        public void setNoBean2(@Nullable Member member) {
            System.out.println("setNoBean2 = "+member);
        }

        /* 의존관계를 주입하는 대상을 선택적으로 설정하고,
         *  Optional<> 타입을 사용하여 대상 객체를 호출한다 : 스프링 빈이 아닌 경우 Optional.empty 호출 */
        @Autowired(required = false)
        public void setNoBean3(Optional<Member> member) {
            System.out.println("setNoBean3 = "+member);
        }
    }
}
