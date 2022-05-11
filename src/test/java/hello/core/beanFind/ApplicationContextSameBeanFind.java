package hello.core.beanFind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFind {

    /* 스프링 컨테이너 생성 & 스프링 빈 등록 (설정정보는 동일한 클래스 내의 @Bean) */
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 빈 조회시, 동일한 타입이 2개 이상이면 중복 오류 발생")
    public void 동일한타입이2개이상() throws Exception {
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 빈 조회시, 동일한 타입이 2개 이상이면 빈 이름을 지정하면 된다")
   public void 동일한타입빈조회() throws Exception {
        MemberRepository bean = ac.getBean("memberRepository1", MemberRepository.class);

        assertThat(bean).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    public void 특정타입빈조회() throws Exception {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);

        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }

        assertThat(beansOfType.size()).isEqualTo(2);
    }

    /* 테스트를 위해 중복되는 타입의 스프링빈을 2개 이상 생성해주기 위해
       설정정보를 담은 AppConfig를 임시로 생성  */
    @Configuration
    static class SameBeanConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
