package com.lakesidehotel.LakeSideHotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Tắt CSRF nếu bạn chỉ phát triển API
        http.csrf().disable();

        // Cho phép tất cả các yêu cầu mà không cần xác thực
        http.authorizeRequests()
                .anyRequest()
                .permitAll();

        // Bỏ qua tạo phiên (nếu không cần trạng thái)
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}
