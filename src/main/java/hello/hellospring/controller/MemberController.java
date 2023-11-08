package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    /* 의존성 주입방법(DI)
        1. 생성자 주입(의존관계가 실행중에 동적으로 바뀔 일은 거의 없으므로 이 방법 추천)
        public MemberController(MemberService memberService) { // MemberController => MemberService => MemberRepository
            this.memberService= memberService;
        }
        2. @Autowired
        3.setter방법
    * */

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member>  members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
