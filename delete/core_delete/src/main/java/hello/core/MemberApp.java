package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    // psvm 치면 메인메소드 만들어짐
    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        // MemberService memberService = new MemberServiceImpl();

        // AppConfig에 있는 환경설정 정보를 가지고, 각각의 @Bean 으로 등록되어 있는 것을 스프링 Bean에다 집어넣고 관리해줌.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);

        Member findMember = memberService.findMember(member.getId());

        System.out.println("new Member = " + member);
        System.out.println("findMember = " + findMember);

    }
}
