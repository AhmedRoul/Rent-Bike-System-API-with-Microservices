package com.RentBikeSystem.api_gateway.Services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "JWT-SERVER",path = "/api/v1/jwt")
public interface JWTServiecs {
    @GetMapping
    @CircuitBreaker(name = "JWT-SERVER", fallbackMethod = "fallbackValidationToken")
    @Retry(name = "JWT-SERVER")
    public ResponseEntity<?> ValidationToken(@RequestBody String token);

    default boolean fallbackValidationToken(String token, Throwable throwable) {
        System.out.println("Fallback method triggered due to: " + throwable.getMessage());
        return false;
    }
}
