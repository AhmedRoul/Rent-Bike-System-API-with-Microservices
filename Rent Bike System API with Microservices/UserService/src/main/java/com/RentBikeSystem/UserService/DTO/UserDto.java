package com.RentBikeSystem.UserService.DTO;

import com.RentBikeSystem.UserService.Model.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private long id;
    private  String fName;
    private  String LName;
    private UserRole userRole;
    private  String email;
    private String password;
}
