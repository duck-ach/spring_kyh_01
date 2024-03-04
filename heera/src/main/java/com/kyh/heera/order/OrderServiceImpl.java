package com.kyh.heera.order;

import com.kyh.heera.discount.DiscountPolicy;
import com.kyh.heera.member.Member;
import com.kyh.heera.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    /**
     * final을 붙여주면 반드시 생성자를 통해 값을 할당해주어야 한다. (Null 허용 X)
     */

    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    /**
     * Setter(수정자) 의존관계 주입
     * 생성하는 시점에 주입 되는 것이 아니기 때문에 field 에서 final 요소를 빼고 setter 메서드를 통해 주입해준다.
     * 보통 선택적이거나 변경 가능성이 있을 경우 사용한다.
     */
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }


    /**
     * 생성자 주입
     */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 할인정책
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();


    // Method Injection
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 회원정보 조회
        Member member = memberRepository.findById(memberId);

        // 할인정책에 회원 넘기기
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 주문에 회원정보와 할인정보 넘기기
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // test 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
