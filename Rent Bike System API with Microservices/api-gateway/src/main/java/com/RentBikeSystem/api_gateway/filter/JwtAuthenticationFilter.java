package com.RentBikeSystem.api_gateway.filter;

import com.RentBikeSystem.api_gateway.Services.JWTServiecs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Predicate;

@RefreshScope
@Component
@Slf4j
public class JwtAuthenticationFilter implements GlobalFilter {
    @Autowired
    private JWTServiecs jwtServiecs;

    private  final  static List<String > APIENDPOINTS=
            List.of("/api/auth/register",
                    "/api/auth/login"
                   , "/eureka");
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();

        Predicate<ServerHttpRequest> isApiSecured =serverHttpRequest -> APIENDPOINTS.stream()
                .noneMatch(s ->serverHttpRequest.getURI().getPath().contains(s) );

        if (isApiSecured.test(request))
        {
            //Authorization in header not exist
            if(!request.getHeaders().containsKey("Authorization"))
            {
               return onError(exchange,"Authorization header is missing",HttpStatus.UNAUTHORIZED);
            }
            log.info("Authorization header found");


            String token = request.getHeaders().getFirst("Authorization");

            if (token != null && token.startsWith("Bearer "))
                token = token.substring(7);
            else
                return onError(exchange,"Invalid token format", HttpStatus.UNAUTHORIZED);

            log.info("Extracted token: {}", token);
            try {
                ResponseEntity<?> responseEntity= jwtServiecs.ValidationToken(token);
                HttpStatus httpStatus= (HttpStatus) responseEntity.getStatusCode();

                if(!httpStatus.equals(HttpStatus.OK)){
                    log.info("Token validation failed");
                    return onError(exchange,responseEntity.getBody().toString(),httpStatus);
                }

            } catch (Exception e) {
                log.error("Error during token validation: {}", e.getMessage());
                return onError(exchange,"JWT Server is down", HttpStatus.SERVICE_UNAVAILABLE);
            }
        }
        return chain.filter(exchange);
    }
    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        byte[] bytes = String.format("{\"error\": \"%s\"}", message).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Mono.just(buffer));
    }


}
