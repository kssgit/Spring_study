package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/**
 * 애플리케이션에대한 동작에대한 구현객체 생성
 * 생성한 객체 인스턴스의 참조(레퍼런스)는 생성자를 통해서 연결(주입)한다
 * 즉, 의존관계 주입을 해준다 DI
 * 역할(interface)과 구현클래스(return값)가 한눈에 들어와야 한다
 */
public class AppConfig {

    public MemberService memberService(){ //추상화에만 의존할 수 있도록 구현체를 AppConfig에서 직접 넣어준다
        //즉, 생성자 주입
        return new MemberServiceImpl(memberRepository());
    }

    //역할(Interface)이 드러나도록
   private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }
}
