package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    //member service를 만드려면 member repository가 필요함. 따라서 얘부터 만들기
    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }  //외부에서 repository 넣어주게 바꿈

    //회원 가입 만들기 (member repository에 save 호출해주기, id 반환해주기)
    public Long join(Member member){

            //같은 이름이 있는 중복 회원 있으면 안된다
            validateDuplicateMember(member); //중복 회원 있는지 검증
            memberRepository.save(member);
            return member.getId(); // 회원가입 하면 id 반환해줌
        }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) //일단 이름 찾아본다. 이 결과는 optional member

                // 이미 그 이름이 존재한다면 ifPresent :null이 아니라 값이 존재하면 동작한다
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                } );
    }

    //전체 회원 조회
    public List<Member> findMembers(){  //Member 객체의 리스트가 반환될 것임을 나타냄
        return memberRepository.findAll();
    }
    //id로 회원 조회
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}

