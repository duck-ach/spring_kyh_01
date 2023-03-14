package hello.startspring.repository;


import hello.startspring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    // memory니까 저장을 해놓을 것을 만들기
    private static Map<Long, Member> store = new HashMap<>();

    // sequence
    private static long sequence = 0L; // 0, 1, 2 이렇게 올라감


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member; // 저장된 것을 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // client 측에서 세팅할 수 있음
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // loop로 돌리는 것
                .filter(member -> member.getName().equals(name))
                .findAny(); // loop를 돌면서 파라미터로 넘어온 name과 같은지 체크하는 것
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // member들이 쭉 반환됨
    }
}
