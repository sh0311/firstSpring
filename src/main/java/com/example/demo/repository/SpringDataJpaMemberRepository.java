package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{

    @Override
    Optional<Member> findByName(String name);  //이렇게 입력하면 select m form Member m where m.name=? 이라고 JPQL쿼리 짜준다
}
