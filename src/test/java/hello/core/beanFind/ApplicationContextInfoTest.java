package hello.core.beanFind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    /* 스프링 컨테이너 생성, 설정정보 주입 */
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    public void 모든빈조회() throws Exception {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); //스프링 컨테이너에 정의된 모든 빈 이름 저장
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }
    }

    @Test
    @DisplayName("모든 빈 출력하기")
    public void 애플리케이션빈조회() throws Exception {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); //스프링 컨테이너에 정의된 모든 빈 이름 저장
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            /* 개발자가 등록한 스프링 빈만 출력
            *  Role ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            *  Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            *  */
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }
        }
    }
}
