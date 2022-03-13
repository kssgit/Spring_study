package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 정액 할인
    // DIP위반 ( 구현체에 의존하기 떄문에 구현체가 바뀌면 new를통해 새로운 구현체를 써줘야한다)
    // DIP를 위배하지 않으려면 OrderServiceImpl의 코드는 바뀌면 안된다 
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();// 정률 할인

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);// 사용자 ID를 사용하여 member객체 반환
        int discountPrice = discountPolicy.discount(member, itemPrice);// 할인된 가격(사용자의 등극에 떄라 다르게 적용)

        return new Order(memberId, itemName , itemPrice, discountPrice);

    }
}
