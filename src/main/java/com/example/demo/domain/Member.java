package com.example.demo.domain;

public class Member {

    private Long id;//시스템이 저장할때 등록해줌. 고객이 정하는 거 아님
    private String name;//회원이 입력

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}