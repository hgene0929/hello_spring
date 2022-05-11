package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    public void 컴포넌트스캔() throws Exception {
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        //when
        MemberService memberService = ac.getBean(MemberService.class);

        //then
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
