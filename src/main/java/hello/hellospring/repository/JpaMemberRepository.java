package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // 이렇게만 하면 JPA가 쿼리 만들어서 데이터베이스에 넣는다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // id로 조회해서 Member.class 타입으로 반환한다.
        return Optional.ofNullable(member);
    }

    // pk 기반이 아닌 나머지 것들 (findByName, findAll) 은 jpql 작성 -> 추후 스프링 데이터 JPA로 사용하면 jpql 안 써도 됨
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // Ctrl + Alt + N 해서 inline -> 합치기
//        List<Member> result = em.createQuery("select m from Member m", Member.class)
//                .getResultList();
//        return result;

        // 객체(Member Entity)를 대상으로 쿼리를 날리는 것
        // select 대상이 객체(Member) 자체임을 기억하기
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
