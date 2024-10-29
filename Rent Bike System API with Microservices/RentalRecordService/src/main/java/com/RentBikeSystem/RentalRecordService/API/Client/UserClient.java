package com.RentBikeSystem.RentalRecordService.API.Client;

import org.springframework.cloud.openfeign.FeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USERSERVICE",path = "/api/v1/user",fallback = UserClientFallback.class)
public interface UserClient {

    @CircuitBreaker(name = "USERSERVICE", fallbackMethod = "fallbackUser")
    @GetMapping("/exist/id/{id}")
    public boolean IsUserExistById(@PathVariable Long id);

    default boolean fallbackUser(Throwable throwable) {
        System.out.println("Fallback method triggered due to: " + throwable.getMessage());
        return false;
    }
}
