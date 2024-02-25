package com.kyh.heera.order;

import com.kyh.heera.AppConfig;
import com.kyh.heera.member.Grade;
import com.kyh.heera.member.Member;
import com.kyh.heera.member.MemberService;
import com.kyh.heera.member.MemberServiceImpl;

public class OrderApp {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

//        MemberService memberService = new MemberServiceImpl(memberRepository);
//        OrderService orderService = new OrderServiceImpl(memberRepository, discountPolicy);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }
}
