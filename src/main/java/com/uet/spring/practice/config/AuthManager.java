package com.uet.spring.practice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

@Component
public class AuthManager implements UserDetailsManager {

    @Autowired
    private PasswordEncoderCustom passwordEncoder;

    @Override
    public void createUser(UserDetails user) {}

    @Override
    public void updateUser(UserDetails user) {}

    @Override
    public void deleteUser(String username) {}

    @Override
    public void changePassword(String oldPassword, String newPassword) {}

    @Override
    public boolean userExists(String username) {
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  User.builder()
                .username("hungpham")
                .password("12345")
                .passwordEncoder(p -> p) // raw -> encrypted. Need to declare a PasswordEncoder Bean
                .roles("USER")
                .build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(this);
        return provider;
    }
}
