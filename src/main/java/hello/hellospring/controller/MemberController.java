package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 클래스에 커서 놓고 (Ctrl+B) 하면 해당 클래스 파일로 이동됨
// 구현된 함수(?)(=구현체)로 이동할 때는 (Ctrl+Alt+B)
// (Alt + Insert) 생성자, Getter/Setter 등

@Controller // 스프링 빈으로 자동 등록 되어 스프링이 관리하게 됨. 스프링 컨테이너에 MemberController 생성
public class MemberController {

    private final MemberService memberService; // 스프링 컨테이너에 하나만 등록하고 공유함

    @Autowired // 스프링이 스프링 컨테이너에 있는 MemberService를 가져와서 연결시킴 -> DI(의존성 주입)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원 등록
    @GetMapping("/members/new")
    public String createForm() {

        return "members/createMemberForm";
    }

    // 회원 등록 처리
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    // 회원 리스트
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
