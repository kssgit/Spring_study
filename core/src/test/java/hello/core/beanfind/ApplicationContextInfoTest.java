package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBeans() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();//등록된 빈 객체들의 이름들을 반환
        for (String beanDefinitionName : beanDefinitionNames) { // iter + tab -> 이터레이터로 배열,리스트의 for문 자동 완성
            // 모든 빈 등록( 스프링 내부 빈까지 출력)
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + ", objec = " + bean);//soutv
        }
    }

    @Test
    @DisplayName("어플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();//등록된 빈 객체들의 이름들을 반환
        for (String beanDefinitionName : beanDefinitionNames) { // iter + tab -> 이터레이터로 배열,리스트의 for문 자동 완성
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);//bean 하나하나에 대한 메타데이터 정

            //개발자가 어플을 개발하기위해 등록한 Bean(또는, 외부 라이브러리)
            //여기서는 AppConfig, memberService 등등
            //Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + ", objec = " + bean);//soutv
            }
        }
    }
}
