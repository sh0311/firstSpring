package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //첫 줄의 memberService에 있는 memberRepository와 두 번째 줄에 있는 memberRepository는 서로 다른 객체라서 서로 다른 db를 쓰게 되는 문제가 발생한다. 따라서 바꾸어야 함
   // MemberService memberService=new MemberService();
   // MemoryMemberRepository memberRepository=new MemoryMemberRepository();

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository=new MemoryMemberRepository();
        memberService= new MemberService(memberRepository); //MemoryMemberRepository를 만들어 넣어주므로써 같은 메모리 리포지토리를 사용하게 됨
    }


    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() { //저장이 잘됐냐
        //given(이러한 상황이 주어졌을 때)
        Member member= new Member();
        member.setName("hello");

        //when(이걸로 실행했을 때)
        Long saveId=memberService.join(member); //join의 반환값이 id라서

        //then(이게 나와야 해)
        Member findMember=memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    //회원 중복검증이 제대로 됐느냐
    public void 중복_회원_예외(){
        //given
        Member member1=new Member();
        member1.setName("spring");

        Member member2=new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e= assertThrows(IllegalStateException.class, () -> memberService.join(member2)); // () -> 뒤의 로직을 따르면 IllegalStateException이라는 예외가 터져야 된다는 뜻

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
   /*
    // memberService.join(member2); 호출 시 IllegalStateException 예외가 발생하면 그 예외 메시지가 "이미 존재하는 회원입니다."인지를 확인하여 예외 처리가 올바르게 동작하는지 테스트하는 것입니다. 만약 예외가 발생하지 않거나 예외 메시지가 다르다면 fail()을 호출하여 테스트를 실패로 표시합니다.
        try{
            memberService.join(member2);
            fail(); //여기까지 오면 실패한 것
        }catch (IllegalStateException e){
            //예외터져서 정상적으로 성공
            assertThat(e.getMessage().isEqualTo("이미 존재하는 회원입니다."))
        }
    */
        //then
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}