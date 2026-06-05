package com.chaitanya.SecurityImpl03.config;

import com.chaitanya.SecurityImpl03.Handler.SuccessHandler;
import com.chaitanya.SecurityImpl03.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig
{
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private SuccessHandler successHandler;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    public UserDetailsService userDetailsService()
    {
        return myUserDetailsService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(myUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security)throws Exception
    {
        return security
                .csrf(csrf ->csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/home","/register/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("USER")
                        .anyRequest()
                        .authenticated()
                ).formLogin(form->form
                        .loginPage("/login")
                        .successHandler(successHandler)
                        .permitAll())

                .exceptionHandling(exception -> exception.accessDeniedPage("/403"))

                .logout(logout -> logout
                        .logoutSuccessUrl("/home").permitAll())
                .build();
    }
}
