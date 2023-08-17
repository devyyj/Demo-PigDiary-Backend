package com.devyyj.pigdiary.security;

import com.devyyj.pigdiary.security.filter.ApiCheckfilter;
import com.devyyj.pigdiary.security.filter.ApiLoginFilter;
import com.devyyj.pigdiary.security.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Log4j2
@EnableMethodSecurity(prePostEnabled = true)
public class Config {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //AuthenticationManager설정
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        // Get AuthenticationManager
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        //반드시 필요
        http.authenticationManager(authenticationManager);

        http
                .csrf(AbstractHttpConfigurer::disable)
//                .formLogin(withDefaults())
//                .oauth2Login(withDefaults())
                .addFilterBefore(apiCheckfilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(apiLoginFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ApiCheckfilter apiCheckfilter(){
        return new ApiCheckfilter(jwtUtil());
    }

    @Bean
    public JWTUtil jwtUtil(){
        return new JWTUtil();
    }

    public ApiLoginFilter apiLoginFilter(AuthenticationManager authenticationManager){
        ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/login", jwtUtil());
        apiLoginFilter.setAuthenticationManager(authenticationManager);

        return apiLoginFilter;
    }
}
