package com.RentBikeSystem.auth_server.Controllers;


import com.RentBikeSystem.auth_server.Entities.AuthResponse;
import com.RentBikeSystem.auth_server.Entities.UserDto;
import com.RentBikeSystem.auth_server.Services.JWTService;
import com.RentBikeSystem.auth_server.Services.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTService jwtService;
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody  UserDto userDto)
    {

        userDto.setPassword(  passwordEncoder.encode(userDto.getPassword()));
        try {
            return userService.addUser(userDto);
        }
        catch (Exception exception){
            throw  new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,"User service is unavailable. Please try again later. ");
        }

    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");

        }
        String Token;
        try {
            Token=jwtService.GenerateToken(userDto.getEmail());
        }
        catch (Exception exception){
            throw  new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,"JwT service is unavailable. Please try again later. ");
        }


        return ResponseEntity.ok(new AuthResponse(Token));
    }



    }
