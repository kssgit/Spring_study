package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);// 사용자 ID를 사용하여 member객체 반환
        int discountPrice = discountPolicy.discount(member, itemPrice);// 할인된 가격(사용자의 등극에 떄라 다르게 적용)

        return new Order(memberId, itemName , itemPrice, discountPrice);

    }
}
