package com.RentBikeSystem.UserService.DTO;


import com.RentBikeSystem.UserService.Model.UserRole;
import com.RentBikeSystem.UserService.Validation.ValidEmail;
import com.RentBikeSystem.UserService.Validation.ValidEnum;
import com.RentBikeSystem.UserService.Validation.ValidName;
import jakarta.validation.constraints.Pattern;
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
    @ValidName( groups = {register.class})
    private String fName;

    @ValidName( groups = {register.class})
    private String lName;

    @ValidEnum(enumClass = UserRole.class,message = "Role must be either ADMIN or CUSTOMER", groups = {register.class})
    private UserRole role;

    @ValidEmail(groups = {login.class,register.class} )
    private String email;

    @Pattern(message = "Password is not valid", regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$"
            ,groups = {login.class,register.class})
    private String password;
    public interface login { }
    public interface register { }




}
