package hello.startspring.repository;

import hello.startspring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// interface에서 interface를 구현할 때는 extends로 표현
// interface는 다중상속이 가능
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    /*
    *
    * 인터페이스를 상속받으면 자체에서 구현체를 만들어 내가 따로 Bean등록을 해주지 않더라도 자기가 알아서 Bean에 등록해 줌
    * */
    @Override
    Optional<Member> findByName(String name);

}
