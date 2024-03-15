package com.kyh.heera.order;

import com.kyh.heera.annotation.MainDiscountPolicy;
import com.kyh.heera.discount.DiscountPolicy;
import com.kyh.heera.member.Member;
import com.kyh.heera.member.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Getter
//@RequiredArgsConstructor
/**
 *  @RequiredArgsConstructor
 *     public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
 *         this.memberRepository = memberRepository;
 *         this.discountPolicy = discountPolicy;
 *     }
 *
 *  @RequiredArgsConstructor :
 *  초기화 되지 않은 final 필드와 @NonNull 어노테이션이 붙은 필드에 대한 생성자 생성
 *  @AllArgsConstructor :
 *  모든 필드에 대한 생성자 생성.
 */
public class OrderServiceImpl implements OrderService {

    /**
     * 생성자 의존관계 주입을 사용하면 final 키워드를 넣어줄 수 있다.
     * final 을 붙여주면 반드시 생성자를 통해 값을 할당해주어야 한다.
     *  - (Null 허용 X)
     *  - (값 변환 X)
     */

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /**
     * Setter(수정자) 의존관계 주입
     * 생성하는 시점에 주입 되는 것이 아니기 때문에 field 에서 final 요소를 빼고 setter 메서드를 통해 주입해준다.
     * 보통 선택적이거나 변경 가능성이 있을 경우 사용한다.
     */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /**
     * 생성자 주입
     */




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

}
