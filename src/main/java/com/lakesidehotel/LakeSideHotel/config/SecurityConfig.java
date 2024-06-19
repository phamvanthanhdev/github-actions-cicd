package com.lakesidehotel.LakeSideHotel.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Tắt CSRF nếu bạn chỉ phát triển API
        http.csrf().disable();
        http.cors(cors->cors.configurationSource(corsCofigrationSource()));

        // Cho phép tất cả các yêu cầu mà không cần xác thực
        http.authorizeRequests()
                .anyRequest()
                .permitAll();

        // Bỏ qua tạo phiên (nếu không cần trạng thái)
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    private CorsConfigurationSource corsCofigrationSource() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration cfg = new CorsConfiguration();
                /*cfg.setAllowedOrigins(Arrays.asList(
                        "http://localhost:5173/vite-deploy"
                ));
                cfg.setAllowedMethods(Collections.singletonList("*"));
                cfg.setAllowCredentials(true);
                cfg.setAllowedHeaders(Collections.singletonList("*"));
                cfg.setExposedHeaders(Arrays.asList("Authorization"));
                cfg.setMaxAge(3600L);*/

                cfg.setAllowCredentials(true);
                cfg.setAllowedOrigins(Arrays.asList(
                        "http://127.0.0.1:5173", "https://phamvanthanhdev.github.io"
                ));
                cfg.setMaxAge(3600L);
                cfg.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
                cfg.addAllowedHeader("origin");
                cfg.addAllowedHeader("content-type");
                cfg.addAllowedHeader("accept");
                cfg.addAllowedHeader("authorization");
                cfg.addAllowedHeader("cookie");

                return cfg;
            }
        };
    }
}
