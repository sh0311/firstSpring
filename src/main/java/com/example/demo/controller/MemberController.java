package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService; //별 기능이 없어서 new해서 객체로 생성할 필요 없이 하나만 생성해서 공유하면 된다. 따라서 이렇게 컨테이너로 등록해주기

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService=memberService;
    }

    @GetMapping("/members/new")  // /members/new로 끝나는 url 입력
    public String createFrom(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")  // /members/new로 끝나는 url 입력
    public String create(MemberForm form){
        Member member=new Member();
        member.setName(form.getName());
        memberService.join(member);

        return "redirect:/"; //회원 가입 끝난 후 홈화면으로 보내 버리는 것
    }

    @GetMapping("/members")   //  저장된 멤버들을 모두 끄집어 내어 리스트에 담은후 view에 넘겨주는역할
    public String list(Model model){
        List<Member> members=memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";

    }


}
