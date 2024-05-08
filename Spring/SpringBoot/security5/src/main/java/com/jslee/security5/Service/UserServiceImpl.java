package com.jslee.security5.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jslee.security5.dto.Users;
import com.jslee.security5.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public int join(Users user) throws Exception {
        int result = userMapper.join(user);

        return result;
    } 
}
