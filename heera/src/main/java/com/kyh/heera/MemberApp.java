package com.kyh.heera;

import com.kyh.heera.member.Grade;
import com.kyh.heera.member.Member;
import com.kyh.heera.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    // psvm 치면 main 메소드 생성 가능
    public static void main(String[] args) {

        /**
         * AppConfig에 있는 환경설정 정보를 가지고 스프링 BEAN에 집어넣어서 관리해 줌
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        /*
            getBean을 할땐 이름을 주어야함.
         */
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        MemberService memberService = new MemberServiceImpl(memberRepository);

        // command + alt + v 
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        // soutv
        System.out.println("findMember = " + findMember.getName());
        System.out.println("member = " + member.toString());

    }
}
