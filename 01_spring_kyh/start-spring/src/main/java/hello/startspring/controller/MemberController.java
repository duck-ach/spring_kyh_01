package hello.startspring.controller;

import hello.startspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    /** Field Dependency는 변경하기 어려우므로 권장하지 않는다. */
//    @Autowired private MemberService memberService;

    /** Constructor Dependency를 권장한다.  */
    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


/** Setter Dependency는 public으로 돌려야해서 권장하지 않는다. */
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }
}
