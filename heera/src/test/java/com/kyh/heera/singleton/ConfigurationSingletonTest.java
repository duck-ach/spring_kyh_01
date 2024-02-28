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

    @Test
    void configurationDeep() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
        /**
         * [result]
         * call AppConfig.memberService
         * call AppConfig.memberRepository
         * call AppConfig.orderService
         * bean = class com.kyh.heera.AppConfig$$SpringCGLIB$$0
         * (스프링이 CGLIB 라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고
         * 그 다른 클래스를 스프링 빈으로 등록한 것이다.)
         *
         * @Configuration
         * @Bean 이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고,
         * 스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.
         * 덕분에 싱글톤이 보장된다.
         */
    }

}
