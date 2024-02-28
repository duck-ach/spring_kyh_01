package com.kyh.heera.member;

public class MemberServiceImpl implements MemberService {

    // new 이후 MemoryMemberRepository 까지 뜬 후, command + shift + enter 까지 입력하면 세미콜론까지 따라온다.
    private final MemberRepository memberRepository;

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

    // test 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
