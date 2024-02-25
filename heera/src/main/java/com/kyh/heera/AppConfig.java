package com.kyh.heera;

import com.kyh.heera.discount.FixDiscountPolicy;
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

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }


}
