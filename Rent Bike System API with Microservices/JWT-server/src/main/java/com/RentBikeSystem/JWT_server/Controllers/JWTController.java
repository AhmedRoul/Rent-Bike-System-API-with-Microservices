package com.RentBikeSystem.JWT_server.Controllers;


import com.RentBikeSystem.JWT_server.Model.Token;
import com.RentBikeSystem.JWT_server.Servers.JwtBlocklistServer;
import com.RentBikeSystem.JWT_server.Utils.JWT;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jwt")
@AllArgsConstructor
@Slf4j
public class JWTController {
    private JWT jwt;
    private JwtBlocklistServer jwtBlocklistServer;

    @GetMapping("/{email}")
    public String GenerateToken(@PathVariable String email){
       String token= jwt.generateToken(email);
        return  token;
    }
    @GetMapping
    public ResponseEntity<?> ValidationToken(@RequestBody String token){
        try {
            if (jwt.validateToken(token)) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token.");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("User service is unavailable. Please try again later.");
        }

    }
    @PostMapping("/token/{token}")
    public boolean AddTokenInBlackList( @PathVariable String token){
        log.info("token");
        log.info(token);
        String email=jwt.extractUsername(token);
        if(email != null&&token != null)
        {
            if(!jwtBlocklistServer.IsExist(token))
            {
                jwtBlocklistServer.AddToken(token,email);
               return true;// return  new ResponseEntity<>("Created Successfully", HttpStatus.CREATED);
            }
            else
                return false;//return ResponseEntity.badRequest().body("Token is exist in block list");
        }
        else
            return false;// return ResponseEntity.badRequest().body("Email and token  inValid");
    }
}
