package com.RentBikeSystem.api_gateway.Config;

import com.RentBikeSystem.api_gateway.filter.JwtAuthenticationFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
 /*   @Bean
    public GlobalFilter jwtAuthenticationFilter(JwtAuthenticationFilter jwtAuthenticationFilter) {
        return jwtAuthenticationFilter;
    }*/
}
