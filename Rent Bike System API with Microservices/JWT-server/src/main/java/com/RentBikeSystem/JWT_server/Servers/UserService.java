package com.RentBikeSystem.JWT_server.Servers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USERSERVICE",path = "/api/v1/user")
public interface UserService {

    @CircuitBreaker(name = "USERSERVICE", fallbackMethod = "fallbackUser")
    @Retry(name = "USERSERVICE")
    @GetMapping("/exist/{email}")
    public boolean IsUserExist(@PathVariable String email);

    default boolean fallbackUser(Throwable throwable) {
        System.out.println("Fallback method triggered due to: " + throwable.getMessage());
        return false;
    }
}
