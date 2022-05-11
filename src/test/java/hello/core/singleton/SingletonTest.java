package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    /* 스프링 없는 순수한 Di 컨테이너가 사용자 요청시 객체를 사용하는 방법 */
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void 싱글톤X() {

        AppConfig appConfig = new AppConfig(); //설정정보 가져오기

        //사용자 요청에 memberService 빈이 필요한 경우(요청한 사용자 : 1)
        MemberService memberService1 = appConfig.memberService();
        //사용자 요청에 memberService 빈이 필요한 경우(요청한 사용자 : 2)
        MemberService memberService2 = appConfig.memberService();

        //동일한 객체임에도 사용자의 요청에 따라 계속해서 새로운 인스턴스 생성
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isNotEqualTo(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    public void 싱글톤O() throws Exception {
        /* 외부에서 새로운 객체 생성은 불가하므로 해당 객체를 생성할 수 X
         *  new SingletonService(); 불가 */

        /* 객체에 대한 인스턴스를 static 영역에 미리 생성해뒀으므로, 조회하여 사용한다
         *  따라서 사용자 요청에 대해 매번 동일한 인스턴스를 사용한다 */
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);

        singletonService1.logic();
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    public void 싱글톤컨테이너() throws Exception {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        /* 스프링 컨테이너에 등록된 객체들은 모두 싱글톤으로 관리된다
        *  따라서 사용자의 요청에 대해 동일한 인스턴스가 사용된다 */
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
