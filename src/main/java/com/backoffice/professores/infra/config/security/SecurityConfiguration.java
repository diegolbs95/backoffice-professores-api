package com.backoffice.professores.infra.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.backoffice.professores.infra.persistencia.enums.PathsConstants.*;
import static com.backoffice.professores.infra.persistencia.enums.Permisao.*;
import static com.backoffice.professores.infra.persistencia.enums.Role.ADMIN;
import static com.backoffice.professores.infra.persistencia.enums.Role.PROFESSOR;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests(authorize -> {

            authorize.requestMatchers("/api/v1/auth/**").permitAll();
            authorize.requestMatchers("/api/v1/professor/registro").permitAll();

            authorize.requestMatchers(PATH_BACKOFFICE + "**").hasRole(ADMIN.name());
            authorize.requestMatchers(PATH_AULA + "**").hasAnyRole(ADMIN.name(), PROFESSOR.name());

            authorize.requestMatchers(POST, PATH_AUTH + "**")
                    .hasAnyAuthority(BACKOFFICE_CREATE.name(), PROFESSOR_CREATE.name());
            authorize.requestMatchers(PUT, PATH_BACKOFFICE + "**")
                    .hasAuthority(BACKOFFICE_UPDATE.name());
            authorize.requestMatchers(POST, PATH_AULA + "**")
                    .hasAnyAuthority(BACKOFFICE_CREATE.name(), PROFESSOR_CREATE.name());
            authorize.requestMatchers(GET, PATH_AULA + "**")
                    .hasAnyAuthority(BACKOFFICE_READ.name(), PROFESSOR_READ.name());
            authorize.requestMatchers(PUT, PATH_AULA + "**")
                    .hasAnyAuthority(BACKOFFICE_UPDATE.name(), PROFESSOR_UPDATE.name());

        })
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(this.jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> {
                    logout.logoutSuccessUrl("/api/v1/auth/logout");
                    logout.addLogoutHandler(logoutHandler);
                    logout.logoutSuccessHandler((request, response, authentication)
                            -> SecurityContextHolder.clearContext());
                })
                .build();

    }
}
