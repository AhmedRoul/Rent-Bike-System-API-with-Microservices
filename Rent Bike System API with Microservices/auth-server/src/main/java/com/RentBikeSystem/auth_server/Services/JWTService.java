package com.RentBikeSystem.auth_server.Services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "JWT-SERVER",path = "/api/v1/jwt")
@CircuitBreaker(name = "JWT-SERVER", fallbackMethod = "fallbackJwt")
@Retry(name = "JWT-SERVER")
public interface JWTService {
    @GetMapping("/{email}")
    public String GenerateToken(@PathVariable String email);
    @PostMapping("/token/{token}")
    public boolean AddTokenInBlackList( @PathVariable String token);

    default boolean fallbackJwt(Throwable throwable) {
        System.out.println("Fallback method triggered due to: " + throwable.getMessage());
        return false;
    }

}
