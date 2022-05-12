package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {

    @Test
    public void 싱글톤스코프() throws Exception {
        //given
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        //when
        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);

        //then
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);

        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);

        ac.close(); //종료 콜백 메서드를 호출하기 위해 스프링 종료료
    }
    @Scope("singleton") //싱글톤 빈스코프를 사용한 설정정보 클래스 생성
    static class SingletonBean {

        @PostConstruct //초기화 콜백 메서드
        public void init() {
            System.out.println("SingletonBean.init");
        }
        @PreDestroy //종료 콜백 메서드
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }
}
