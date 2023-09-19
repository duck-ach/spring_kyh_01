package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // 인터페이스만 가지고 있으면 NullPointException 이 뜬다. -> 구현체 지정
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {

    }

    @Override
    public Member findMember(Long memberId) {
        return null;
    }
}
