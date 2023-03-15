package com.vnco.cartservice.security;

import com.vnco.cartservice.user.UserService;
import com.vnco.common.model.user.Role;
import com.vnco.common.model.user.UserSecurity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    private final UserService     userService;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return encoder;
    }
    
    @Bean
    UserDetailsService userDetailsService() {
        return username -> {
            UserSecurity user = userService.getUserSecurityByUsername(username);
            log.info(">> UserSecurity: {}", user);
            return User.withUsername(user.getUsername())
                       .password(user.getPassword())
                       .roles(user.getRoles().stream().map(
                               Role::getName).toArray(String[]::new))
                       .build();
        };
    }
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        h2ConsoleConfig(http);
        secureUrlConfig(http);
        formLogin(http);
        return http.build();
    }
    
    private void h2ConsoleConfig(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
    
    private void formLogin(HttpSecurity http) throws Exception {
        http.formLogin()
            .loginPage("/auth/login/form")
            .usernameParameter("username")
            .passwordParameter("password")
            .loginProcessingUrl("/auth/login")
            .failureUrl("/auth/login/fail")
            .defaultSuccessUrl("/auth/login/success", false)
            .and().rememberMe().rememberMeParameter("remember");
        
        http.logout().logoutUrl("/auth/logout").logoutSuccessUrl("/auth/logout/success");
    }
    
    private void secureUrlConfig(HttpSecurity http) throws Exception {
        
        http.authorizeHttpRequests(
                (authorizeRequest) -> authorizeRequest
                                              .requestMatchers("/admin/**")
                                              .hasAnyRole("STAFF", "DIRECTOR")
                                              .requestMatchers("/cart/checkout/**")
                                              .authenticated()
                                              .anyRequest().permitAll());
    }
}
