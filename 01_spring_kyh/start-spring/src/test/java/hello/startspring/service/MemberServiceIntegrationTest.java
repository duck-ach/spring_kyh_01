package hello.startspring.service;

import hello.startspring.domain.Member;
import hello.startspring.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행한다.
@Transactional  // 테스트 케이스에 이 애너테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후에 항상 롤백한다.
                // (이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.)
class MemberServiceIntegrationTest {

    /**
     * 테스트 코드는 메소드 이름을 한글로 자주 짓는다. (영어권 사람들과 협업할 때 제외)
     *
     * given when then 문법. (Test 할 때 나누기 좋은 문법)
     */
    @Autowired
    MemberService memberService; // 여기는 선언만
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() {

        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // 예외의 타입들도 지정할 수 있음. NullPointerException 하면 오류

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


    }

}