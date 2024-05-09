package com.jslee.security5.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Users {
    private int userNo;
    private String userId;
    private String userPw;          // 암호화된 비밀번호
    private String userPwCheck;     // 암호화되지않은 비밀번호
    private String name;
    private String email;
    private Date regDate;
    private Date updDate;
    private int enabled;

    // 권한 목록
    List<UserAuth> authList;
}