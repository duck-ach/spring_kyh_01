package com.kyh.heera.singleton;

import com.kyh.heera.AppConfig;
import com.kyh.heera.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("Spring 없는 순수 DI Container")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        //1. 조회 : 호출할 때 마다 객체 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회 : 호출할 때 마다 객체 생성
        MemberService memberService2 = appConfig.memberService();

        //참조 값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        /**
         * [Result]
         * memberService1 = com.kyh.heera.member.MemberServiceImpl@26794848
         * memberService2 = com.kyh.heera.member.MemberServiceImpl@302552ec
         */

        // memberService1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

    }

}
