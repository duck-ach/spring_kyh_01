package hello.startspring.service;

import hello.startspring.domain.Member;
import hello.startspring.repository.MemberRepository;
import hello.startspring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*; // 이 요소에 대고 option + enter
import static org.junit.jupiter.api.Assertions.*;


class MemberServiceTest {

    /**
     * 테스트 코드는 메소드 이름을 한글로 자주 짓는다. (영어권 사람들과 협업할 때 제외)
     *
     * given when then 문법. (Test 할 때 나누기 좋은 문법)
     */
    MemberService memberService; // 여기는 선언만
    MemoryMemberRepository repository = new MemoryMemberRepository(); // clear() 가져오기위해 import

    @BeforeEach
    public void beforeEach() {
        repository = new MemoryMemberRepository(); // repository를 만들고,
        memberService = new MemberService(repository); // memberService에 넣어줌으로써, 같은 repository를 사용한다. (DI)
    }

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void 회원가입() {

        // given
        Member member = new Member();
        member.setName("heeroong");

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

        /*
        try {
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름 예외");
        }
        */

        // then


    }

    @Test
    void 모든회원조회() {

        // given


        // when

        // then

    }

    @Test
    void 회원조회() {
    }


}