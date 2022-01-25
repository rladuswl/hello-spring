package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
}
