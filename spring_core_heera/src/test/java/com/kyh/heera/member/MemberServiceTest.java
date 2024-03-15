package com.kyh.heera.member;

import com.kyh.heera.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
* Test 유닛은 빌드될 때 함께 빌드되지 않는다.
* */
public class MemberServiceTest {

    MemberService memberService;

    /**
     * @BeforeEach
     * 테스트가 실행되기 전에 무조건 실행되는 코드
     */
    @BeforeEach
    public  void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        // Assert core 의 api 사용하면 됨
        Assertions.assertThat(member).isEqualTo(findMember);

    }
}
