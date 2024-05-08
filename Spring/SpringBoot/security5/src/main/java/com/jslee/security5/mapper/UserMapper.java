package com.jslee.security5.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.jslee.security5.dto.Users;

@Mapper
public interface UserMapper {
    // 🔐 로그인 (사용자 인증)
    public Users login(String username);

    // 회원가입
    public int join(Users user) throws Exception;
}
