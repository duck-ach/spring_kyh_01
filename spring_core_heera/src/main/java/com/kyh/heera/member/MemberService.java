package com.kyh.heera.member;

public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);

}
