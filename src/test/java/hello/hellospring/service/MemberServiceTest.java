package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

// given, when, then 문법
// 무언가가 주어졌다, 이것을 실행했을 때, 그러면 결과가 이렇게 나와야 한다.
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach  // 테스트 실행 전에 실행
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach  // 돌 때마다 메모리(DB) 클리어
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    public void 회원가입() throws Exception {
        // Given
        Member member = new Member();
        member.setName("hello");

        // When
        Long saveId = memberService.join(member);

        // Then = 검증
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        // Given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // When
        memberService.join(member1);

        // 블럭 드래그 하고 (Ctrl + Shift + /) -> 블럭 주석처리
        /*try {
            memberService.join(member2);
            fail(); // 바로 윗 줄에서 에러가 터졌어야 함. 이 줄이 실행되면 에러 발생시키기 실패.
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/


        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2)); // member2도 가입을 하려하면 예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}