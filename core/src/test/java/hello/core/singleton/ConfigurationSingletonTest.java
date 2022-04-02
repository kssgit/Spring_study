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
}
