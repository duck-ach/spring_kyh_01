package com.kyh.heera;

import com.kyh.heera.discount.DiscountPolicy;
import com.kyh.heera.discount.FixDiscountPolicy;
import com.kyh.heera.member.MemberRepository;
import com.kyh.heera.member.MemberService;
import com.kyh.heera.member.MemberServiceImpl;
import com.kyh.heera.member.MemoryMemberRepository;
import com.kyh.heera.order.OrderService;
import com.kyh.heera.order.OrderServiceImpl;

public class AppConfig {

    /**
     * AppConfig
     * 객체를 생성하고 연결하는 책임을 가지는 별도의 설정 클래스
     */

    /**
     * [Refactoring]
     * 아래와 같이 AppConfig를 구성하면
     * Method 를 호출하는 순간 역할이 다 드러난다.
     */
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }

}
