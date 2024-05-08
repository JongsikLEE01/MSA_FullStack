package com.jslee.security5.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.jslee.security5.security.CustomAcessDeniedHandle;
import com.jslee.security5.security.CustomDetailsService;
import com.jslee.security5.security.LoginSuccessHandler;

@Configuration          // 스프링 빈 설정 클래스로 지정
@EnableWebSecurity      // 스프링 시큐리티 설정 빈으로 등록
public class SecurityConfig{

    @Autowired
    private DataSource dataSource;  // appliction.properties에 정의한 DB정보

    @Autowired
    private CustomDetailsService customDetailsService;

    /*
     * ✔ 인증(Authentication)
     * : 등록된 사용자인지 확인하여 입증
     * 
     * ✔ 인가(Authorization)
     * : 사용자의 권한을 확인하여 권한을 따라 자원 사용 범위를 구분하여 허락하는 것
     */

    /**
     * 🔐 스프링 시큐리티 설정 메소드
     * ✔ 인가 설정
     * ✔ 로그인 설정
     * ✔ 로그아웃 설정
     * ✔ 자동 로그인 설정
     * ✔ 예외 처리 설정
     * ✔ CSRF 방지 기능 설정
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        // 인가 설정
        // .antMatchers("인가URL")
        //              .permitAll()                    : 모든 사용자 접근 가능
        //              .hasRole("ROLE_USER")           : ROLE_USER 권한 접근 허용 
        //              .hasAnyRole("USER", "ADMIN")    : USER, ADMIN 권한 접근 허용

        // anyRequest()          : 설정하지 않은 경로 외에 모든 요청에 대하여 설정
        //   .authenticated()    : 인증된 사용자만 접근 허용
        http.authorizeRequests(requests ->requests
            // 어드민 경로 설정 -> 어드민 접근 허용
            .antMatchers("/admin","/admin/**").hasRole("ADMIN")
            // 유저 경로 설정 -> 유저, 어드민 접근 허용
            .antMatchers("/user","/user/**").hasAnyRole("USER","ADMIN")
            // css, js, img -> 모든 접근 허용
            .antMatchers("/css/**","/js/**","/img/**").permitAll()
            .antMatchers("/**").permitAll()
            .anyRequest().authenticated())
            ;

            // ✔ 로그인 설정
            // 폼 기반 로그인 활성화
            // - 기본 설정 : 시큐리티 제공 로그인 페이지
            /*
             * 로그인 커스텀 설정
             * defaultSuccessUrl()  : 로그인 성공 시, 이동 경로 지정
             * loginPage()          : 커스텀 로그인 페이지 지정,     기본값 -> ("/login")
             * loginProcessingUrl() : 커스텀 로그인 처리 경로 기정,  기본값 -> ("/login")
             * usernameParameter()  : 아이디 요청 파라미터 이름 지정
             * passwordParameter()  : 비밀번호 요청 파라미터 이름 지정
             * successHandler()     : 로그인 성공 시, 처리할 빈 지정
             *                         아이디 저장 (쿠키)
             */
            http.formLogin()
                    .defaultSuccessUrl("/")
                    .loginPage("/login")
                    .loginProcessingUrl("/loginPro")
                    .usernameParameter("id")
                    .passwordParameter("pw")
                    .successHandler(authenticationSuccessHandler())
                    .permitAll();

            // 사용자 정의 인증 설정
            http.userDetailsService(customDetailsService);

            // ✔ 로그아웃 설정
            // logoutSuccessUrl("URL") : 로그아웃 성공 시, 이동할 URL 지정 -> 기본값은 ("login")
            // logoutUrl("URL")        : 로그아웃 처리 요청 경로 지정("logout")
            http.logout().logoutSuccessUrl("/")
                        .logoutUrl("/logout")
                        .permitAll();

            // ✔ 자동 로그인 설정
            // key()                    : 자동 로그인에서 토큰 생성/검증에 사용되는 식별 키
            // tokenRepository()        : 토큰을 저장할 저장소 지정(데이터소스 포함한 저장소 객체)
            // tokenValiditySeconds()   : 토큰 유효시간 설정
            http.rememberMe().key("jslee")
                            .tokenRepository(tokenRepository())
                            .tokenValiditySeconds(60 * 60 * 24 * 7);

            // ✔ 인증 예외 처리
            // accessDeniedPage()       : 접근 거부 시, 이동 경로 지정
            // accessDeniedHandler()    : 접근 거부 시, 처리할 빈 지정
            http.exceptionHandling()
                            // .accessDeniedPage("/exception")
                            .accessDeniedHandler(accessDeniedHandler())
                            ;

            // ✔ CSRF 방지 설정
            // - 기본 설정 : CSRF 방지 보안 설정
            // ❓ CSRF (Cross Site Request Forgery)
            // 사이트 간 요청 위조 (공격)
            // http.csrf().disable();  // CSRF 방지 비활성화

            return http.build();
    }

    // 🔐 사용자 인증 설정
    // - 인메모리 방식
    // - JDBC 인증 방식 인증

    // JDBC 인증 방식
    // ✔ 데이터 소스 (URL, ID, PW) - application.properties
    // ✔ SQL 쿼리 등록
    //      - 사용자 인증 쿼리
    //      - 사용자 권한 쿼리
    // @Bean
    // public UserDetailsService userDetailsService(){
    //     // 데이터 소스
    //     JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

    //     // 사용자 인증 쿼리
    //     String sql1 = " SELECT user_id as username, user_pw as password, enabled "
    //                 + " FROM user "
    //                 + " WHERE user_id = ? ";

    //     // 사용자 권한 쿼리
    //     String sql2 = " SELECT user_id as username, auth "
    //                 + " FROM user_auth "
    //                 + " WHERE user_id = ? ";

    //     userDetailsManager.setUsersByUsernameQuery(sql1);
    //     userDetailsManager.setAuthoritiesByUsernameQuery(sql2);
    //     return userDetailsManager;
    // }

    
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

    /**
     * ✔ 자동 로그인 저장소 빈 등록
     * - 데이터 소스 지정
     * persistent_logins 테이블 생성
            create table persistent_logins (
            username varchar(64) not null
            , series varchar(64) primary key
            , token varchar(64) not null
            , last_used timestamp not null);
     * @return
     */
    @Bean
    public PersistentTokenRepository tokenRepository(){
        // JdbcTokenRepositoryImpl : 토큰 저장 데이터베이스를 등록하는 객체
        JdbcTokenRepositoryImpl repositoryImpl = new JdbcTokenRepositoryImpl();

        // persistent_logins (자동 로그인) 테이블 생성
        // repositoryImpl.setCreateTableOnStartup(true);

        // 토큰 저장소를 사용하는 데이터 소스 지정
        repositoryImpl.setDataSource(dataSource);

        return repositoryImpl; 
    }

    /**
     * 접근 거부 에러 처리 빈 등록
     * @return
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAcessDeniedHandle();
    }

    /**
     * 인증 성공 처리
     * @return
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new LoginSuccessHandler();
    }
}