package com.RentBikeSystem.JWT_server.Scheduling;

import com.RentBikeSystem.JWT_server.Model.IJwtBlock;
import com.RentBikeSystem.JWT_server.Model.JwtBlock;
import com.RentBikeSystem.JWT_server.Servers.JwtBlocklistServer;
import com.RentBikeSystem.JWT_server.Utils.JWT;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
@Slf4j
public class JWTToken {
    private final JWT jwt;
    private final  JwtBlocklistServer jwtBlocklistServer;

    @Scheduled(fixedRateString ="100000" /*"${Scheduled.jwt.fixedRate}"*/)
    public void removeExpiredTokens() {
        List<JwtBlock> allTokens = jwtBlocklistServer.getAllToken();  // Retrieve all tokens
        for (JwtBlock token : allTokens) {
          log.info(  "Value:"+token.getToken());//jwt.isTokenExpired( token.getToken()).toString());

           if( jwt.isTokenExpired( token.getToken()))
           {
               log.info(  "deleted:");
               jwtBlocklistServer.DeleteToken(token.getToken());

           }
        }
    }
}
