package com.practice.book.config.auth;

import com.practice.book.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile", "/login").permitAll()
                .antMatchers("/admin/**").hasRole(Role.ADMIN.name())
                .antMatchers("/api/v1/**").hasAnyRole(Role.USER.name(),Role.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/authError")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login().loginPage("/login")
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
        ;
    }

}
