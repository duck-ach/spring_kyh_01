package com.kyh.heera.singleton;

import com.kyh.heera.AppConfig;
import com.kyh.heera.member.MemberRepository;
import com.kyh.heera.member.MemberServiceImpl;
import com.kyh.heera.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = memberService.getMemberRepository();


        System.out.println("memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        /**
         * [Result]
         * memberRepository1 = com.kyh.heera.member.MemoryMemberRepository@4b7e96a
         * memberRepository2 = com.kyh.heera.member.MemoryMemberRepository@4b7e96a
         * memberRepository = com.kyh.heera.member.MemoryMemberRepository@4b7e96a
         */

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

}
