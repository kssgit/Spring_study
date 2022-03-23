package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//option + Enter -> static으로 등록
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        //검증
        //option + Enter -> static으로 등록
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); // 빈으로 등록된 객체가 구현체 MemberServiceImpl과 같은지 검

    }

    //해당 인터페이스 타입으로 등록된 빈
    @Test
    @DisplayName("이름없이 타입으로만 조회") // 인터페이스 타입으로
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        //검증
        //option + Enter -> static으로 등록
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); // 빈으로 등록된 객체가 구현체 MemberServiceImpl과 같은지 검

    }

    //역할이 똑같은 다른 구현체를 구분하기위해 구현체 타입으로 빈 조회해야 하는경우도 있다
    //잘 쓰진 않는다( 구현체에 의존하기 떄문에 유연성이 떨어진다)
    @Test
    @DisplayName("구현체 타입으로 조회")
    void findBeanByName2(){
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        //검증
        //option + Enter -> static import
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); // 빈으로 등록된 객체가 구현체 MemberServiceImpl과 같은지 검

    }

    //실패 Test( 반드시 해야한다)
    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findBeanByNameX(){
        //ac.getBean("xxx",MemberService.class);
//        MemberService xxx = ac.getBean("xxx", MemberService.class);
        //NoSuchBeanDefinitionException    발생
        assertThrows(NoSuchBeanDefinitionException.class,
                ()->ac.getBean("xxx", MemberService.class));
    }
}
