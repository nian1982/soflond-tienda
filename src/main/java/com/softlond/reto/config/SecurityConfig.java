package com.softlond.reto.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
        http.authorizeExchange(exchanges -> exchanges
                .pathMatchers(HttpMethod.GET, "/producto/**", "/tienda/**").hasAnyRole("ADMIN", "USER")
                .pathMatchers(HttpMethod.POST, "/producto", "/tienda").hasRole("ADMIN")
                .pathMatchers(HttpMethod.PUT, "/producto/**", "/tienda/**").hasRole("ADMIN")
                .pathMatchers(HttpMethod.DELETE, "/producto/**", "/tienda/**").hasRole("ADMIN")
                .anyExchange().authenticated()
            )
            .httpBasic(withDefaults())
            .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .exceptionHandling(exceptions -> exceptions
                    .authenticationEntryPoint((exchange, ex) -> {
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
                        var json = "{\"success\":false,\"message\":\"Credenciales invalidas o no proporcionadas\",\"data\":null,\"timestamp\":\"" + java.time.LocalDateTime.now() + "\",\"status\":401}";
                        var buffer = exchange.getResponse().bufferFactory().wrap(json.getBytes(StandardCharsets.UTF_8));
                        return exchange.getResponse().writeWith(Mono.just(buffer));
                    })
                    .accessDeniedHandler((exchange, ex) -> {
                        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
                        var json = "{\"success\":false,\"message\":\"No tienes permisos para acceder a este recurso\",\"data\":null,\"timestamp\":\"" + java.time.LocalDateTime.now() + "\",\"status\":403}";
                        var buffer = exchange.getResponse().bufferFactory().wrap(json.getBytes(StandardCharsets.UTF_8));
                        return exchange.getResponse().writeWith(Mono.just(buffer));
                    })
            );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
