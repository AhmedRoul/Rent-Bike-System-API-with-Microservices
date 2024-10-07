package com.RentBikeSystem.UserService.Repository;

import com.RentBikeSystem.UserService.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>
{
    Optional<User> findByEmailAndPassword(String Email,String Password);
    Optional<User> findByEmail(String Email);


}
