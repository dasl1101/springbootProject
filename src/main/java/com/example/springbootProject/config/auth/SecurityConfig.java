package com.example.springbootProject.config.auth;

import com.example.springbootProject.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests() //authorizeRequests가 선언되어야만 antMatchers옵션을 사용가능
                .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll() //전체유저 열람권한
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //유저만 열람가능
                .anyRequest().authenticated()//나머지는 인증된 사용자(로그인한 사용자)에게만 열람가능
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }
}
