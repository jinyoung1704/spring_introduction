package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller
public class MemberController {
    private final MemberService memberService;
    @Autowired // 스프링 컨테이너에 있는 스프링 빈을 주입받고자 할 때 사용하는 어노테이션
    public MemberController(MemberService memberService) { // MemberController => MemberService => MemberRepository
        this.memberService= memberService;
    }

    /*
     * private final MemberService memberService = new MemberService();
     *  이렇게 하면 MemberController가 아닌 다른 곳에서도 MemberService를 사용하게 됨
     * */
}