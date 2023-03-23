package hello.startspring.config;

import hello.startspring.controller.MemberController;
import hello.startspring.repository.JdbcMemberRepository;
import hello.startspring.repository.JdbcTemplateMemberRepository;
import hello.startspring.repository.MemberRepository;
import hello.startspring.repository.MemoryMemberRepository;
import hello.startspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }

    // Controller는 Spring 이 관리하는거라서 여기에 따로 등록하지않고 Controller파일에서 하기

}
