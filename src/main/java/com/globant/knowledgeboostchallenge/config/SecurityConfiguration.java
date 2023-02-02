package com.globant.knowledgeboostchallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return  http.csrf().disable().authorizeHttpRequests().requestMatchers("/weather/**").authenticated()
                .and().httpBasic().and().authorizeHttpRequests().requestMatchers("/swagger-ui/**")
                .permitAll().anyRequest().permitAll().and().build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder()
                .username("gerardo.salvador")
                .password(encoder().encode("globant"))
                .roles("USER")
                .build();
        UserDetails user2 = User.builder()
                .username("luffy")
                .password(encoder().encode("globant"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user1,user2);
    }
}
