package com.RentBikeSystem.RentalRecordService.Filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RequestFilter extends OncePerRequestFilter {
    @Autowired
    DiscoveryClient discoveryClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String remoteAddr = httpRequest.getRemoteAddr();

        boolean isAllowed = discoveryClient.getServices().stream()
                .flatMap(service -> discoveryClient.getInstances(service).stream())
                .map(ServiceInstance::getHost)
                .anyMatch(host -> host.equals(remoteAddr));
        if (isAllowed) {
            filterChain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
        }
    }

}
