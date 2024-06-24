package com.jslee.security5.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jslee.security5.dto.CustomUser;
import com.jslee.security5.dto.Users;
import com.jslee.security5.mapper.UserMapper;

@Service
public class CustomDetailsService implements UserDetailsService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // MyBatis 사용해서 사용자 정보 조회
        Users user = userMapper.login(username);

        if(user == null){
            throw new UsernameNotFoundException("사용자를 찾을 수 없음..."+username);
        }

        // 🔐 CustomUser -> UsersDetails 변환 작업 수행
        CustomUser customUser = new CustomUser(user);
        return customUser;
    }
}
