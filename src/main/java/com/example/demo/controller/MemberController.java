package com.example.demo.controller;

import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService; //별 기능이 없어서 new해서 객체로 생성할 필요 없이 하나만 생성해서 공유하면 된다. 따라서 이렇게 컨테이너로 등록해주기

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService=memberService;
    }

}
