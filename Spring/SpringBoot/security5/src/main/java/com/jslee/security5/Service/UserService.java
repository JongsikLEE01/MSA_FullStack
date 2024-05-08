package com.jslee.security5.Service;

import com.jslee.security5.dto.Users;

public interface UserService {
    // 회원가입
    public int join(Users user) throws Exception;
}
