package com.kyh.heera.member;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component // ("memberService2") bean 이름도 지정 가능
public class MemberServiceImpl implements MemberService {

    // new 이후 MemoryMemberRepository 까지 뜬 후, command + shift + enter 까지 입력하면 세미콜론까지 따라온다.
    private final MemberRepository memberRepository;

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
