package com.RentBikeSystem.auth_server.Config;

import com.RentBikeSystem.auth_server.Services.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
@AllArgsConstructor
public class LogoutService implements LogoutHandler {
    @Autowired
    private JWTService jwtService;
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);


       boolean saved;
       try {
           saved= jwtService.AddTokenInBlackList(jwt);
       }
       catch (Exception exception){
           throw  new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,"JwT service is unavailable. Please try again later. ");
       }

        if(saved) {
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            try {
                response.getWriter().write("{\"message\": \"Logout successful\"}");
                response.getWriter().flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
