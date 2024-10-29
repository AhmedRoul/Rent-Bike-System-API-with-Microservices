package com.RentBikeSystem.auth_server.Services;

import com.RentBikeSystem.auth_server.Entities.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
   private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto=null;
        try{
        userDto= userService.getUser(username);

       }
        catch (Exception exception){
            ResponseStatusException ex = new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                    "User service is unavailable. Please try again later.");

            throw ex;
        }
        if (userDto==null){
            log.info("here!!");
            throw new UsernameNotFoundException("User not found");

        }

          return User.withUsername(userDto.getEmail())
                .password(userDto.getPassword())
                .authorities("USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
