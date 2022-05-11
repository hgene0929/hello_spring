package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    public void 상태싱글톤() throws Exception {
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //when
        // Thread A : A사용자가 10000원 주문
        statefulService1.order("userA", 10000);
        // Thread B : B사용자가 10000원 주문
        statefulService2.order("userB", 20000);

        //then
        // Thread A : 사용자 A 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = "+price);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    @Test
    public void 무상태싱글톤() throws Exception {
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatelessService statelessService1 = ac.getBean(StatelessService.class);
        StatelessService statelessService2 = ac.getBean(StatelessService.class);

        //when
        int userAPrice = statelessService1.order("userA", 10000);
        int userBPrice = statelessService2.order("userB", 20000);

        //then
        System.out.println("userA price = "+userAPrice);
        System.out.println("userB price = "+userBPrice);

        Assertions.assertThat(userAPrice).isEqualTo(10000);
        Assertions.assertThat(userBPrice).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
        @Bean
        public StatelessService statelessService() {
            return new StatelessService();
        }
    }
}