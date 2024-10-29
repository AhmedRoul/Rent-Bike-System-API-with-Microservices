package com.RentBikeSystem.UserService.Services.Imp;


import com.RentBikeSystem.UserService.DTO.UserDto;
import com.RentBikeSystem.UserService.Model.User;
import com.RentBikeSystem.UserService.Repository.UserRepository;
import com.RentBikeSystem.UserService.Services.UserService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImp implements UserService  {
    @Autowired
    private  UserRepository  userRepository;
    @Autowired
     ModelMapper modelMapper;

    @Override
    public boolean IsExist(String Email) {

        return userRepository.existsByEmail(Email);
    }

    @Override
    public UserDto loginUser(String Email, String Password) {

        Optional<User> optionalUser = userRepository.findByEmailAndPassword(Email,Password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserDto  userDto=modelMapper.map(user, UserDto.class);
            System.out.println("Mapped UserDto: " + userDto);
            return userDto;
        } else {
            return null;
        }
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        log.info(userDto.toString());
        User user=modelMapper.map(userDto,User.class);
        /*user.setId(null);*/
        User save=userRepository.save(user);
        log.info(save.toString());
        UserDto savedDto=modelMapper.map(save,UserDto.class);
        log.info(savedDto.toString());
        return savedDto;
    }

    @Override
    public UserDto updateUserPatch(long id, Map<String, Object> attributeUpdate) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            attributeUpdate.forEach((key, value) -> {
                try {
                    Field field = User.class.getDeclaredField(key);
                    field.setAccessible(true);

                    Class<?> fieldType = field.getType();
                    if (fieldType.equals(float.class) && value instanceof Double) {
                        field.set(user, ((Double) value).floatValue());
                    } else if (fieldType.equals(double.class) && value instanceof Float) {
                        field.set(user, ((Float) value).doubleValue());
                    } else {
                        field.set(user, value);
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException("Error updating user field: " + key, e);
                }
            });
            return updateUser(id,modelMapper.map(user, UserDto.class));

        }
        return null;

    }

    @Override
    public UserDto updateUser(long id, UserDto userDto) {
        if(userDto.getId()!=0&&id!=userDto.getId()){
            return null;
        }
        Optional<User> optionalUser =userRepository.findById(id);
        if(optionalUser.isPresent())
        {
            User user =modelMapper.map(userDto,User.class);
            User Updated=  userRepository.save(user);
            UserDto userDto1 =modelMapper.map(Updated,UserDto.class);
            return userDto1;
        }
        return null;
    }


    @Override
    public UserDto getUser(Long id) {
        Optional<User> user=userRepository.findById(id);
        if(user.isPresent())
            return modelMapper.map(user.get(),UserDto.class);
        return null;
    }

    @Override
    public UserDto getUser(String Email) {

        Optional<User> user=userRepository.findByEmail(Email);

        if(user.isPresent())
            return modelMapper.map(user.get(), UserDto.class);


        return null;
    }

    @Override
    public UserDto getUser(String Email, String Password) {
        Optional<User> user=userRepository.findByEmailAndPassword(Email,Password);
        if(user.isPresent())
            return modelMapper.map(user.get(),UserDto.class);
        return null;
    }

    @Override
    public boolean DeleteUser(String Email) {

        Optional<User> user=userRepository.findByEmail(Email);
        if (user.isPresent()){
            userRepository.delete(user.get());
            return true;
        }
        return false;
    }



}
