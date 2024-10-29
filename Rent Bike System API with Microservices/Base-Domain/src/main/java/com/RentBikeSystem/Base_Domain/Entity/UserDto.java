package com.RentBikeSystem.Base_Domain.Entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;

    @Pattern(regexp = "^[a-zA-Z]+", message = "Should be only English characters")
    @Size(min = 3, max = 20, message = "First name must be at least 8 characters long, at most 20 characters")
        private String fName;

    @Pattern(regexp = "^[a-zA-Z]+", message = "Should be only English characters")
    @Size(min = 3, max = 20, message = "First name must be at least 8 characters long, at most 20 characters")
    private String lName;

    @NotNull
    private UserRole role;

    @Email(message = "Email is not valid", regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")
    private String email;

    @Pattern(message = "Password is not valid", regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
    private String password;
}


