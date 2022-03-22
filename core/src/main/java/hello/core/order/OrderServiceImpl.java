package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

/**
 * 클라이언트 영역
 * 실행하는 책임만 담당
 * DIP, OCP 준수
 * 소프트웨어의 요소를 새롭게 확장해도 사용영역의 변경은 닫혀있다
 */
public class OrderServiceImpl implements OrderService{
    /**
     * DIP 추상화(Interface)에만 의존
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; // 할인 정책
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 정액 할인
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();// 정률 할인
    // DIP위반 ( 구현체에 의존하기 떄문에 구현체가 바뀌면 new를통해 새로운 구현체를 써줘야한다)
    // DIP를 위배하지 않으려면 OrderServiceImpl의 코드는 바뀌면 안된다
    // 구현체를 OrderServiceImpl에서 선택해서는 안된다 즉, 인터페이스만 의존해야 하고 구현체는 의존해서는 안된다


    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);// 사용자 ID를 사용하여 member객체 반환
        int discountPrice = discountPolicy.discount(member, itemPrice);// 할인된 가격(사용자의 등극에 떄라 다르게 적용)

        return new Order(memberId, itemName , itemPrice, discountPrice);

    }
}
