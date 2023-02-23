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

    // 이렇게 new로 선언해서 쓰지 않고 스프링에 의존 관계 설정.
    // MemberController말고 다른 컨트롤러들이 가져다 쓰지 않도록 스프링에 의존 관계를 설정해주어야 함.
    // private final MemberService memberService = new MemberService();

    private final MemberService memberService;

    // 연결하는 법 - 생성자 작성 후 @Autowired 달기
    // 의존성 주입(dependency injection) : 이렇게 하면 MemberController가 생성이 될 때, 스프링 빈에 등록되어 있는 memberService객체를 가져다가 연결시켜줌.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
