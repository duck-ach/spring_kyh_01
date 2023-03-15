package hello.startspring.repository;

import hello.startspring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*; // option + enter

// 다른곳에 가져다 쓸게 아니므로 public을 지움
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 각각의 Test가 끝날 때 마다 실행
    public void afterEach() { // callback method
        repository.clearStore(); // Test에 사용된 공용 데이터 등을 clear를 해줌
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring"); // command + shift + enter 하면 커서가 어디있든 줄바꿈

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // Optional로 주었으므로, get()으로 바꿈

        // System.out.println("result = " + (result == member));
        // Assertions.assertEquals(member, result);
        // assertThat(member).isEqualTo(result); // member는 result와 똑같다.

        // option + enter를 눌러 import를 하면 assertThat()을 한번에 사용할 수 있다.

        assertThat(member).isEqualTo(result);

    }

    @Test
    void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test // test 실행 단축 : ctrl + shift + r
    void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }



}
