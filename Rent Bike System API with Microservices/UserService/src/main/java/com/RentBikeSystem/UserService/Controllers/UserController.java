package com.RentBikeSystem.UserService.Controllers;


import com.RentBikeSystem.UserService.DTO.UserDto;

import com.RentBikeSystem.UserService.Services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
@Slf4j
public class UserController {
    private  UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody @Validated(UserDto.login.class)  UserDto userDto){
        userService.loginUser(userDto.getEmail(),userDto.getPassword());
        return ResponseEntity.ok("");
    }
    @GetMapping("/{email}")
    public UserDto getUser(@PathVariable String email){
        return userService.getUser(email);
    }
    @GetMapping("/exist/{email}")
    public boolean IsUserExist(@PathVariable String email){
        return userService.IsExist(email);
    }

    @PostMapping
    public ResponseEntity<?> addUser(@Validated(UserDto.register.class) @RequestBody   UserDto userDto){

            UserDto saved = userService.saveUser(userDto);
            if (saved != null)
                return ResponseEntity.ok(saved);

            else
                return ResponseEntity.badRequest().body("Not save exist problem");
    }
}
