package com.RentBikeSystem.UserService.Config;

import com.RentBikeSystem.UserService.Filters.RequestFilter;
import org.springframework.beans.factory.annotation.Autowired;


/*@Configuration
@EnableWebSecurity*/

public class SecurityConfig {
    @Autowired
    private RequestFilter requestFilter;

   /* @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);
        return  http.build();
    }*/

}
