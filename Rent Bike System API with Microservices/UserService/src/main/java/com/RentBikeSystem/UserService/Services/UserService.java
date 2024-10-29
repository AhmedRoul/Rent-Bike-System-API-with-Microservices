package com.RentBikeSystem.UserService.Services;

import com.RentBikeSystem.UserService.DTO.UserDto;
import java.util.Map;

public interface UserService {
    boolean IsExist(String Email);
    UserDto loginUser(String Email,String Password);

    UserDto saveUser(UserDto userDto);
    UserDto updateUserPatch(long id, Map<String, Object> attributeUpdate);
    UserDto updateUser(long id ,UserDto userDto);
    UserDto getUser(Long id);
    UserDto getUser(String Email);
    UserDto getUser(String Email,String Password);
    boolean DeleteUser(String Email);



}
