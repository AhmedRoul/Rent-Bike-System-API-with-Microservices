package com.RentBikeSystem.auth_server.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.el.parser.Token;

@Data
@AllArgsConstructor
public class AuthResponse {
   private String  Token;
}
