package com.jslee.springresponse.dto;

import lombok.Data;

@Data
public class Person {
    private int age;
    private String name;

    // 기본 생성자
    public Person() {
        this.name = "김조은";
        this.age = 20;
    }
}