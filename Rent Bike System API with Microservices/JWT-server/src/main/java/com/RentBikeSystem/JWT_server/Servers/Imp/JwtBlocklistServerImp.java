package com.RentBikeSystem.JWT_server.Servers.Imp;

import com.RentBikeSystem.JWT_server.Model.IJwtBlock;
import com.RentBikeSystem.JWT_server.Model.JwtBlock;
import com.RentBikeSystem.JWT_server.Repository.JWTBlockRepository;
import com.RentBikeSystem.JWT_server.Servers.JwtBlocklistServer;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class JwtBlocklistServerImp implements JwtBlocklistServer {

    private JWTBlockRepository jwtBlockRepository;

    @Override
    public List<JwtBlock> getAllToken() {
        return jwtBlockRepository.findAll();
    }

    @Override
    @CachePut(value = "jwt", key = "#Token")
    public boolean AddToken(String Token, String Email) {
        JwtBlock jwtBlocklist=new JwtBlock(Token ,Email);
        try {
            jwtBlockRepository.save(jwtBlocklist);
            return true;
        }
        catch (Exception e){
            return false;
        }

    }

    @Override
    @Cacheable(key = "#Token",value = "jwt")
    public boolean IsExist(String Token) {
        return jwtBlockRepository.existsById(Token);
    }

    @Override
    @CacheEvict(key = "#Token",value = "jwt")
    public void DeleteToken(String Token) {
        if (jwtBlockRepository.existsById(Token)) {
            jwtBlockRepository.deleteById(Token);
        }
    }

}
