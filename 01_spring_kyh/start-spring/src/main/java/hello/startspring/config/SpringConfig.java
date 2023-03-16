package hello.startspring.config;

import hello.startspring.controller.MemberController;
import hello.startspring.repository.MemberRepository;
import hello.startspring.repository.MemoryMemberRepository;
import hello.startspring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // Controller는 Spring 이 관리하는거라서 여기에 따로 등록하지않고 Controller파일에서 하기

}
