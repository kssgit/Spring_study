package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberServiceImpl = ac.getBean(MemberServiceImpl.class);
        OrderServiceImpl orderServiceImpl = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean(MemberRepository.class);

        //모든 MemberRepository의 객체가 같음을 확인할 수 있다
        System.out.println("MemberServiceImpl -> memberRepository = " + memberServiceImpl.getMemberRepository());
        System.out.println("orderServiceImpl -> memberRepository= " + orderServiceImpl.getMemberRepository());
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberServiceImpl.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderServiceImpl.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);

        //스프링 컨테이너는 ApplConfig를 상속받은 다른 임이의 클래스를 생성한다
        //CGLIB라는 바이코드 조작 라이브러리를 사용
        //만약 @Configuration을 붙이지 않는다면 싱글톤이 깨진
        //즉, 순수한 자바코드가 실행되어 MemberRepository가 3번 호출된다
        System.out.println("bean = " + bean.getClass());
    }
}
