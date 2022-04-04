package com.uet.spring.practice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EnableWebMvc
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthManager authManager;

//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        var user = User.builder()
//                .username("hungpham")
//                .password("12345")
//                .passwordEncoder(p -> p) // raw -> encrypted. Need to declare a PasswordEncoder Bean
//                .roles("USER")
//                .authorities("USER")
//                .build();
//
//        var admin =  User.builder()
//                .username("anboo33")
//                .password("12345")
//                .passwordEncoder(p -> p) // raw -> encrypted. Need to declare a PasswordEncoder Bean
//                .roles("ADMIN")
//                .authorities("ADMIN")
//                .build();
//        // return authManager; //Custom
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("anboo33")
                .password("12345")
                .roles("ADMIN")
                .authorities("ADMIN")
                .and()
                .withUser("hungpham")
                .password("12345")
                .authorities("USER")
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable().csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/v2/hello").hasRole("ADMIN")
            .antMatchers("/", "/actuator/**").permitAll() // Cho phép tất cả mọi người truy cập vào 2 địa chỉ này
            .anyRequest().authenticated() // Tất cả các request khác đều cần phải xác thực mới được truy cập
            .and()
            .formLogin() // Cho phép người dùng xác thực bằng form login
            .defaultSuccessUrl("/api/v1/users")
            .permitAll() // Tất cả đều được truy cập vào địa chỉ này
            .and()
            .logout() // Cho phép logout
            .permitAll();
    }
}
