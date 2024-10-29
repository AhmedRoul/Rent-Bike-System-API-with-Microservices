package com.RentBikeSystem.UserService.Service;

import com.RentBikeSystem.UserService.DTO.UserDto;
import com.RentBikeSystem.UserService.Model.User;

import com.RentBikeSystem.UserService.Model.UserRole;
import com.RentBikeSystem.UserService.Repository.UserRepository;
import com.RentBikeSystem.UserService.Services.Imp.UserServiceImp;
import com.RentBikeSystem.UserService.Services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository  userRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private UserService userService=new UserServiceImp();

    @Test
    public void testLoginUserValid(){
        User user = User.builder()
                .role(UserRole.CUSTOMER)
                .id(1L)
                .email("ahmedNagy@gmail.com")
                .password("Mypassword@1225")
                .lName("Ahmed N")
                .fName("Ahmed N")
                .build();

        UserDto userDto = UserDto.builder()
                .role(UserRole.CUSTOMER)
                .id(1L)
                .email("ahmedNagy@gmail.com")
                .password("Mypassword@1225")
                .lName("Ahmed N")
                .fName("Ahmed N")
                .build();
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

        when(userRepository.findByEmailAndPassword("ahmedNagy@gmail.com", "Mypassword@1225"))
                .thenReturn(Optional.of(user));

        UserDto exist = userService.loginUser("ahmedNagy@gmail.com", "Mypassword@1225");
        verify(userRepository).findByEmailAndPassword("ahmedNagy@gmail.com", "Mypassword@1225");

        Assertions.assertNotNull(exist, "UserDto should not be null");
        Assertions.assertEquals("ahmedNagy@gmail.com", exist.getEmail(), "Email should match the user");
    }
    @Test
    public void testLoginUserInValid(){

        when(userRepository.findByEmailAndPassword("existinguser@gmail.com", "ValidPassword1235"))
                .thenReturn(Optional.empty());

        UserDto savedUserDto = userService.loginUser("existinguser@gmail.com", "ValidPassword1235");
        Assertions.assertNull(savedUserDto);

    }

    @Test void testSaveUserValid()
    {
        User user = User.builder()
                .role(UserRole.CUSTOMER)
                .id(1L)
                .email("ahmedNagy@gmail.com")
                .password("Mypassword@1225")
                .lName("Ahmed N")
                .fName("Ahmed N")
                .build();

        UserDto userDto = UserDto.builder()
                .role(UserRole.CUSTOMER)
                .id(1L)
                .email("ahmedNagy@gmail.com")
                .password("Mypassword@1225")
                .lName("Ahmed N")
                .fName("Ahmed N")
                .build();
        when(modelMapper.map(Mockito.any(UserDto.class), Mockito.eq(User.class))).thenReturn(user);
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        when(modelMapper.map(Mockito.any(User.class), Mockito.eq(UserDto.class))).thenReturn(userDto);

        UserDto savedUserDto = userService.saveUser(userDto);

        Assertions.assertNotNull(savedUserDto);
        Assertions.assertEquals(savedUserDto.getEmail(), "ahmedNagy@gmail.com");
    }


}
