package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository {

    //밑에 있는 함수에서 member save할 때 저장을 어디가에 해야 돼서 만듦
    private static Map<Long, Member> store = new HashMap<>();

    //sequence:0,1,2 key 값 생성해주기 위한 애
    private static long sequence = 0L;

    //회원 저장기능
    @Override
    public Member save(Member member) {
        member.setId(++sequence); //store하기전에 id값 세팅해줌
        store.put(member.getId(), member); //id값 세팅한 후 멤버를 store에 저장해줌
        return member; //member에 id와 name이 저장됨

    }

    //회원 아이디로 찾기
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //결과가 없어 null이 반환될 가능성있으면 optional로 감싸준다.
    }

    //이름으로 찾기
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //loop 돌린다
                .filter(member -> member.getName().equals(name)) //parameter로 넘어온 name 과 getName값이 같은지 비교. 같은 경우에만 필터링 됨
                .findAny();                                      // Map에서 루프를 다 돌면서 하나 찾으면 찾은 값 반환. 없으면 optional에 null이 포함돼서 반영됨

    }

    //전체 회원 리스트 보기
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //values=members 멤버들을 반환
    }


    public void clearStore() {
        store.clear();
    }
}
