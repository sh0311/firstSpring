package com.example.demo.controller;

public class MemberForm {
    private String name;  //createMemberForm.html에서 input된 name="name"에서 name을 스프링이 보고 여기다가 저장. 그 후 setName을통해 넣어줌

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
