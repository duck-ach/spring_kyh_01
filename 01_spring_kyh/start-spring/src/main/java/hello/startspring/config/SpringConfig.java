package hello.startspring.config;

import hello.startspring.repository.MemberRepository;
import hello.startspring.service.MemberService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
    public SpringConfig(@Qualifier("springDataJpaMemberRepository") MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
}
