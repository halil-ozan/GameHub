package com.springboot.videogame.security;



import com.springboot.videogame.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class DemoSecurityConfig {

    //bcrypt bean definition
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //authenticationProvider bean definition
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationSuccessHandler customAuthenticationSuccessHandler) throws Exception {

        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/profile").hasRole("EMPLOYEE")
                                .requestMatchers("/games").hasRole("EMPLOYEE")
                                .requestMatchers("/home").hasRole("EMPLOYEE")
                                .requestMatchers("/register/**").permitAll()
                                .requestMatchers("/user-profile/**").hasRole("EMPLOYEE")
                                .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .successHandler(customAuthenticationSuccessHandler)
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .permitAll()
                                .logoutSuccessUrl("/")
                )
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied")
                );

        return http.build();
    }

}