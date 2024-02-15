package com.kyh.heera.member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);

}
