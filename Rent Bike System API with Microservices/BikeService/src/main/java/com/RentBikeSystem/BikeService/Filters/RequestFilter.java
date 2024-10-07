    package com.RentBikeSystem.BikeService.Filters;

    import jakarta.servlet.FilterChain;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.cloud.client.discovery.DiscoveryClient;
    import org.springframework.stereotype.Component;
    import org.springframework.web.filter.OncePerRequestFilter;
    import org.springframework.cloud.client.ServiceInstance;

    import java.io.IOException;
    import java.util.List;

    @Component
    @Slf4j
    public class RequestFilter extends OncePerRequestFilter {
        @Autowired
        DiscoveryClient discoveryClient;

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

            filterChain.doFilter(request, response);
        }

    }
