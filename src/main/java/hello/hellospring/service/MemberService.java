package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Ctrl + Shift + T 누르면 Create Test

//@Service // 어노테이션 넣어줘야 스프링 컨테이너에 등록됨
public class MemberService {

    private final MemberRepository memberRepository;

    //@Autowired // MemberService 생성할 때 스프링 컨테이너에서 MemberRepository를 가져다가 넣어줌 (사실상 MemberRepository의 구현체가 MemoryMemberRepository이므로 이게 주입됨)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {

        // 같은 이름이 있는 중복 회원 X
        // Optional로 감싸서 반환했기 때문에 ifPresent 같이 쓸 수 있음.
        // ifPresent는 result가 null이 아니라 어떤 값이 있으면 수행

        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */

        // 위에 코드를 더 깔끔하게 쓰기
        /*
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
         */

        // 위에 코드 메서드로 뽑기 -> 단축기 ctrl+shift+alt+T
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * memberId에 해당하는 회원 한 명 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
