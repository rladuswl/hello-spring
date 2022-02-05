package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

// 자바 코드로 직접 스프링 빈 등록하기

@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository()); // 스프링 빈으로 등록된 memberRepository 넣어줌
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository(); // 구현체를 new 해야함. MemberRepository는 인터페이스라서 new 불가능
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
