package com.RentBikeSystem.JWT_server.Repository;

import com.RentBikeSystem.JWT_server.Model.IJwtBlock;
import com.RentBikeSystem.JWT_server.Model.JwtBlock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JWTBlockRepository extends JpaRepository<JwtBlock,String> {

     /*List<IJwtBlock> findByToken();*/

}
