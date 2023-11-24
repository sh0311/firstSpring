package com.example.demo.repository;

import com.example.demo.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; //jpa는 Entity manager를 이용하여 모든 게 동작한다

    public JpaMemberRepository(EntityManager em){ //스프링부트가 만들어준 entity manager를 주입 받는다
        this.em=em;
    }
    @Override
    public Member save(Member member) {
        em.persist(member); // persist : 영구 저장. JPA가 insert query만들어서 db에 다 저장해주고 set id까지 다 해준다
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member=em.find(Member.class, id); // 조회 : find(조회할 타입, 식별자)
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result=em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name) //JPQL에서 사용된 파라미터 :name에 실제 값을 매핑. 이 경우 name 파라미터에는 메서드의 매개변수로 전달된 name이 들어감
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
