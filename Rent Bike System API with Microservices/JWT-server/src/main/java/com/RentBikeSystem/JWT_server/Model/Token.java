package com.RentBikeSystem.JWT_server.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Token {
    private boolean isTokenExpired;
    private  String email;
    private  String token;
}
