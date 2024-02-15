package hello.startspring.repository;

import hello.startspring.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member); // 회원이 저장소에 저장
    Optional<Member> findById(Long id); // 아이디로 찾기
    Optional<Member> findByName(String name); // 이름으로 찾기
    List<Member> findAll(); // 모두 찾기

}
