package com.example.demo;

import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;
import com.example.demo.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean  //MemberService를 스프링 빈에등록 + 스프링 빈에 등록된 repository를 넣어준다(autowired 역할)
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean   //Repository를 스프링 빈에등록 (MemberRepository는 인터페이스, MemoryMemberRepository가 구현체
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
