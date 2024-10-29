package com.RentBikeSystem.UserService.Repository;


import com.RentBikeSystem.UserService.Model.User;

import com.RentBikeSystem.UserService.Model.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    // Test case for findByEmailAndPassword()
    public void testFindByEmailAndPassword(){
        User user = User.builder()
                .fName("john")
                .lName("doe")
                .password("password123")
                .role(UserRole.ADMIN)
                .email("john.doe@example.com")
                .build();

       User saved= userRepository.save(user);

        Optional<User> foundUser = userRepository.findByEmailAndPassword("john.doe@example.com", "password123");

        assertTrue(foundUser.isPresent(), "User should be found");
        assertEquals("john.doe@example.com", foundUser.get().getEmail(), "Emails should match");
        assertEquals("password123", foundUser.get().getPassword(), "Passwords should match");

    }
    @Test
    // Test case for findByEmailAndPassword()
    public void testFindByEmailAndPasswordNotFound(){

        Optional<User> foundUser = userRepository.findByEmailAndPassword("john.doe@example.com", "password123");

        assertTrue(foundUser.isEmpty(), "User should be not found");
    }
    @Test
    // Test case for findByEmail()
    public void testFindByEmail(){
        User user = User.builder()
                .fName("john")
                .lName("doe")
                .password("password123")
                .role(UserRole.ADMIN)
                .email("john.doe@example.com")
                .build();

        User saved= userRepository.save(user);

        Optional<User> foundUser = userRepository.findByEmail("john.doe@example.com");
        assertTrue(foundUser.isPresent(), "User should be found");
        assertEquals("john.doe@example.com", foundUser.get().getEmail(), "Emails should match");
    }
    @Test
    // Test case for findByEmailAndPassword()
    public void testFindByEmailNotFound(){

        Optional<User> foundUser = userRepository.findByEmailAndPassword("john.doe@example.com", "password123");

        assertTrue(foundUser.isEmpty(), "User should be not found");
    }

}
