package com.glb.knowledgeboostchallenge.filter.security;

import java.util.Objects;

import com.glb.knowledgeboostchallenge.filter.AuthEntryPoint;
import com.glb.knowledgeboostchallenge.filter.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static com.glb.knowledgeboostchallenge.utils.Constants.WEATHER_API;

@Configuration
public class SecurityConfiguration {

    @Value("${app.http.auth-token-header-name}")
    private String principalRequestHeader;

    @Value("${app.http.auth-token}")
    private String principalRequestValue;

    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthFilter filter = new AuthFilter(principalRequestHeader);
        filter.setAuthenticationManager(
            authentication -> {
                String principal = (String) authentication.getPrincipal();
                if (!Objects.equals(principalRequestValue, principal)) {
                    throw new BadCredentialsException(
                        "The API key was not found or not the expected value.");
                }
                authentication.setAuthenticated(true);
                return authentication;
            });
        http.antMatcher(WEATHER_API + "/**")
            .csrf()
            .disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(filter)
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(authEntryPoint);

        return http.build();
    }

}
