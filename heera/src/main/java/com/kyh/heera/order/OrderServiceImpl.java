package com.kyh.heera.order;

import com.kyh.heera.discount.DiscountPolicy;
import com.kyh.heera.member.Member;
import com.kyh.heera.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    /**
     * final을 붙여주면 반드시 생성자를 통해 할당해주어야 한다.
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 할인정책
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 회원정보 조회
        Member member = memberRepository.findById(memberId);

        // 할인정책에 회원 넘기기
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 주문에 회원정보와 할인정보 넘기기
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
