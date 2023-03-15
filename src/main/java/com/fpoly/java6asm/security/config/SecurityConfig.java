//package com.fpoly.java6asm.security.interceptor;
//
//import com.fpoly.java6asm.common.mapper.UserSecurityMapper;
//import com.fpoly.java6asm.user.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.interceptor.annotation.web.builders.HttpSecurity;
//import org.springframework.security.interceptor.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
////@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//    private final UserService     userService;
//    private final UserSecurityMapper userSecurityMapper;
//    private final PasswordEncoder encoder = new BCryptPasswordEncoder();
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return encoder;
//    }
//
//    @Bean
//    UserDetailsService userDetailsService() {
//        return username -> userSecurityMapper.toUserSecurity(userService.getByUsername(username));
//    }
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        h2ConsoleConfig(http);
//        secureUrlConfig(http);
//        authenticationConfig(http);
//        return http.build();
//    }
//
//    private void h2ConsoleConfig(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
//    }
//
//    private void authenticationConfig(HttpSecurity http) throws Exception {
//        http.formLogin()
//            .loginPage("/auth/login/form")
//            .usernameParameter("username")
//            .passwordParameter("password")
//            .loginProcessingUrl("/auth/login")
//            .failureUrl("/auth/login/fail")
//            .defaultSuccessUrl("/auth/login/success", false)
//            .and().rememberMe().rememberMeParameter("remember");
//
//        http.logout().logoutUrl("/auth/logout").logoutSuccessUrl("/auth/logout/success");
//    }
//
//    private void secureUrlConfig(HttpSecurity http) throws Exception {
//
//        http.authorizeHttpRequests((authorizeRequest) -> authorizeRequest.requestMatchers("/cart/checkout/**").authenticated()
//                                                                     .anyRequest().permitAll());
//    }
//}
