package com.RentBikeSystem.auth_server.Services;

import com.RentBikeSystem.auth_server.Entities.UserDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "USERSERVICE",path = "/api/v1/user")
@CircuitBreaker(name = "USERSERVICE", fallbackMethod = "fallbackUser")
@Retry(name = "USERSERVICE")
public interface UserService {
    @GetMapping("/{email}")
    UserDto getUser(@PathVariable("email") String email);
    @PostMapping
    ResponseEntity<?> addUser(@RequestBody UserDto userDto);

    default boolean fallbackUser(Throwable throwable) {
        System.out.println("Fallback method triggered due to: " + throwable.getMessage());
        return false;
    }

}
