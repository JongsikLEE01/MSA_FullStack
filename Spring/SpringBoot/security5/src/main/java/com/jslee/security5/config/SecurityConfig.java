package com.jslee.security5.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration          // 스프링 빈 설정 클래스로 지정
@EnableWebSecurity      // 스프링 시큐리티 설정 빈으로 등록
public class SecurityConfig{

    @Autowired
    private DataSource dataSource;  // appliction.properties에 정의한 DB정보

    // 기본 설정
    // - 인메모리 방식
    // - JDBC 인증 방식 인증

    // JDBC 인증 방식
    // ✔ 데이터 소스 (URL, ID, PW) - application.properties
    // ✔ SQL 쿼리 등록
    //      - 사용자 인증 쿼리
    //      - 사용자 권한 쿼리
    @Bean
    public UserDetailsService userDetailsService(){
        // 데이터 소스
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        // 사용자 인증 쿼리
        String sql1 = " SELECT user_id as username, user_pw as password, enabled "
                    + " FROM user "
                    + " WHERE user_id = ? ";

        // 사용자 권한 쿼리
        String sql2 = " SELECT user_id as username, auth "
                    + " FROM user_auth "
                    + " WHERE user_id = ? ";

        userDetailsManager.setUsersByUsernameQuery(sql1);
        userDetailsManager.setAuthoritiesByUsernameQuery(sql2);
        return userDetailsManager;
    }

    
    /**
     * 🎯 인메모리 방식 인증
     * - 기본 사용자를 메모리에 등록
     * - user / 123456
     * - admin / 132456
     * @return user, admin
     */
    // @Bean
    // public UserDetailsService userDetailsService(){
        //     UserDetails user = User.builder()
        //                             .username("user")   // 아이디
        //                             .password(passwordEncoder().encode("123456")) // 패스워드
        //                             .roles("USER")      // 권한
        //                             .build();
        //     UserDetails admin = User.builder()
        //                             .username("admin")
        //                             .password(passwordEncoder().encode("123456"))
        //                             .roles("USER","ADMIN")
    //                             .build();
    //     return new InMemoryUserDetailsManager(user, admin);
    // }
    
    // authenticationManger 빈 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
       return authenticationConfiguration.getAuthenticationManager();
    }

    // 암호화 방식 빈 등록
    @Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    }
}