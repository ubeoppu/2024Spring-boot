package com.kr.solo.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("================securityFilterChain==============");

        http.formLogin()
                .loginPage("/member/login")
                        .defaultSuccessUrl("/main")
                                .usernameParameter("member_email")
                                        .failureUrl("/member/login/error")
                                                .and()
                                                        .logout()
                                                                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                                                                        .logoutSuccessUrl("/main");

        http.authorizeRequests()
                        .mvcMatchers("/member/login", "/member/register", "/main").permitAll()
                        .mvcMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated();

        http.exceptionHandling()
                        .authenticationEntryPoint(new Http403ForbiddenEntryPoint());

        http.csrf().disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}
}
