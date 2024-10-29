package com.RentBikeSystem.JWT_server.Utils;

import com.RentBikeSystem.JWT_server.Model.Token;
import com.RentBikeSystem.JWT_server.Servers.JwtBlocklistServer;
import com.RentBikeSystem.JWT_server.Servers.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Component
@Slf4j
public class JWT {

    private final String Secret;
    private final long expirationMillis;
    private final JwtBlocklistServer jwtBlocklistServer;

    private final UserService userService;


    @Autowired
    public JWT(@Value("${jwt.secret}") String Secret,
               @Value("${jwt.expirationMillis}") long expirationMillis,
               JwtBlocklistServer jwtBlocklistServer,
               UserService userService) {
        this.Secret = Secret;
        this.expirationMillis = expirationMillis;
        this.jwtBlocklistServer = jwtBlocklistServer;
        this.userService =userService;

    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration );
    }
    public  <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final  Claims claims=extarctAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extarctAllClaims(String token){


        return Jwts.parser().setAllowedClockSkewSeconds(60).setSigningKey(getSignInKey()).parseClaimsJws(token).getBody();
    }
    public Boolean isTokenExpired(String token){
        log.info("extractExpiration(token) :"+extractExpiration(token).toString());
        long differenceInMillis = extractExpiration(token).getTime() - (new Date()).getTime();

        // Convert milliseconds to minutes
        long minutesDifference = differenceInMillis / (1000 * 60);
        log.info("extractExpiration(token) :"+minutesDifference);
        return extractExpiration(token).before(new Date());

    }

    public String generateToken(String Email){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims,Email);
    }
    private String createToken(Map<String,Object>claims,String subject){

        final String SECRET_KEY_BASE64 = Base64.getEncoder().encodeToString(Secret.getBytes());
        Date now =new Date(System.currentTimeMillis());
        Date exp = new Date(now.getTime() + expirationMillis);

            return Jwts.builder()

                    .setClaims(claims).
                    setSubject(subject).
                    setIssuedAt(now)
                    .setExpiration(exp)
                    .signWith(SignatureAlgorithm.HS256, getSignInKey()).compact();
    }
    public Boolean validateToken(String token){
        final String username =extractUsername(token);
        boolean user;
        try{
            user = userService.IsUserExist(username) ;
        }
        catch (Exception exception){
            throw new RuntimeException("User service is currently unavailable, cannot validate user: " + username);

        }
        return  !isTokenExpired(token)
                &&jwtBlocklistServer.IsExist(token) &&user;
    }
    private Key getSignInKey() {
        String secretWithAlgo = Secret + SignatureAlgorithm.HS256.toString();
        return Keys.hmacShaKeyFor(secretWithAlgo.getBytes());

    }


}
