package com.kyh.heera;

import com.kyh.heera.member.Grade;
import com.kyh.heera.member.Member;
import com.kyh.heera.member.MemberService;
import com.kyh.heera.member.MemberServiceImpl;

public class MemberApp {
    // psvm 치면 main 메소드 생성 가능
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        // command + alt + v 
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        // soutv
        System.out.println("findMember = " + findMember.getName());
        System.out.println("member = " + member);

    }
}
