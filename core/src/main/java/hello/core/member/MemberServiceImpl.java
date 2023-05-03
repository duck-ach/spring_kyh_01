package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 인터페이스의 구현체가 하나만 있을 경우 관례적으로 이름에 Impl을 많이 붙인다.
@Component("memberService2")
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    // ac.getBean(memberRepository.class) == @Autowired
    @Autowired // 의존관계를 자동으로 주입해주는 기능 Component를 사용하면 @Autowired를 쓰게 된다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
