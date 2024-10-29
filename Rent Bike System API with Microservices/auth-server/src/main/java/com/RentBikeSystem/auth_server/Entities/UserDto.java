package com.RentBikeSystem.auth_server.Entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;

    //@Pattern(regexp = "^[a-zA-Z]+", message = "Should be only English characters")
    @Size(min = 3, max = 20, message = "First name must be at least 3 characters long, at most 20 characters")
    private String fName;

    //@Pattern(regexp = "^[a-zA-Z]+", message = "Should be only English characters")
    @Size(min = 3, max = 20, message = "Last name must be at least 3 characters long, at most 20 characters")
    private String lName;

    private UserRole role;

    @Email(message = "Email is not valid")
    private String email;

    @Pattern(message = "Password is not valid", regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
    private String password;
}



