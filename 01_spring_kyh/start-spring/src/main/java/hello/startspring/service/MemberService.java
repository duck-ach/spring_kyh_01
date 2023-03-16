package hello.startspring.service;

import hello.startspring.domain.Member;
import hello.startspring.repository.MemberRepository;
import hello.startspring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService { // command + shift + t 를 누르면 test를 간편하게 생성할 수 있음

    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) { // 같은이름을 가진 중복회원은 받지 않음.

        // 1
    /*
        Optional<Member> result = memberRepository.findByName(member.getName());// command + option + v
        result.ifPresent(m -> {
           throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    */

        // 2
    /*
        // 이미 repository에서 Optional을 사용했기 때문에, 여기서는 위와같이 코드를 짜지않고, 생략해도 된다.
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    */

        // 3
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 조회
     * @param memberId
     * @return one member
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
