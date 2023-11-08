package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository=new MemoryMemberRepository(); //MemberRepository 객체 repository가 MemoryMemberRepository객체 가리킴으로써 얘의 메소드와 데이터에 접근가능해짐

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member=new Member();
        member.setName("spring");

        repository.save(member);//repository에 저장. Id도 저절로 할당되어 저장되게 앞에서 구현했었음

        Member result=repository.findById(member.getId()).get();//optional에서 값을 꺼낼 때는 get을 이용해서 바로 꺼낼 수 있다
        Assertions.assertThat(member).isEqualTo(result);// member랑 result랑 같은지 확인하는 기능( member가 result랑 똑같은지)
    }

    @Test
    public void findByName(){
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result=repository.findByName("spring1").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result=repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }

}
