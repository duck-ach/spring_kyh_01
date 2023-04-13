package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; // 이렇게만 하면 NullPointerExceptions
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override // 주문 요청이 오면
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 회원을 생성하고
        Member member = memberRepository.findById(memberId);
        // 할인 정책 먹이고
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 최종 생성된 주문을 반환해준다.
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
