package hello.core.member;

// 인터페이스의 구현체가 하나만 있을 경우 관례적으로 이름에 Impl을 많이 붙인다.
public class MemberServiceImpl implements MemberService {

    // cmd + shift + enter 하면 세미콜론까지 완성
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
