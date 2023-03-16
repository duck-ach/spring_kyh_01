package hello.startspring.controller;

import hello.startspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // Spring이 객체를 자동으로 생성해서 가지고 있다. (Bean이 관리된다.)
public class MemberController {
    private final MemberService memberService;

    // Spring이 관리되게 되면 new로 새로 객체를 생성하기보다 Spring에 올려둔 것을 가져다 쓰면 된다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }








}
