package com.jslee.security5.dto;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CustomUser implements UserDetails{

    // 사용자 DTO
    private Users user;

    public CustomUser(Users user){
        this.user = user;
    }

    /**
     * 🔐 권한 정보 메소드
     * ✔ UserDetails를 CustomUser로 구현해서
     *  Spring Security의 User 대신 사용자 정의 인증 객체(CustomUser)로 적용
     * ❗ CustomUser 적용 시, 권한을 사용할 때는 'ROLE_' 붙여서 사용해야함
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthList().stream()
                                 .map((auth) -> new SimpleGrantedAuthority(auth.getAuth()))
                                 .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getUserPw();
        // return "PROTECTED";
    }

    @Override
    public String getUsername() {
        return user.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 만료되면 true, 만료되지 않으면 false
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 만료되면 true, 만료되지 않으면 false
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 만료되면 true, 만료되지 않으면 false
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 만료되면 true, 만료되지 않으면 false
        return user.getEnabled() == 0 ? false : true;
    }

}