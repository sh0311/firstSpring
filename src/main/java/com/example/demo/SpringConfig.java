package com.example.demo;

import com.example.demo.aop.TimeTraceAop;
import com.example.demo.repository.*;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration  //조립하는 코드!! 설정 파일을 만들거나 Bean을 등록하기 위한 어노테이션.  @Bean을 사용하는 클래스의 경우 반드시 @Configuration을 같이 사용해야 한다.
public class SpringConfig {

    private final MemberRepository memberRepository; //외부에서 MemberRepository 를 제공받을 것을 나타냄

    @Autowired
    public SpringConfig(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }



    @Bean  //MemberService를 스프링 빈에등록 + 스프링 빈에 등록된 repository를 넣어준다(autowired 역할)
    public MemberService memberService(){
        return new MemberService(memberRepository);  //MemberService에 의존 관계 세팅해줌. return 값을 통해 받은 MemberService를 spring bean에 등록해줌
    }

    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
   /* @Bean   //Repository를 스프링 빈에등록 (MemberRepository는 인터페이스, MemoryMemberRepository가 구현체
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);
    }*/
}
