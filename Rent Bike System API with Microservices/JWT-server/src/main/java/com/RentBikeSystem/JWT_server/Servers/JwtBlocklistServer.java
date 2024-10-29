package com.RentBikeSystem.JWT_server.Servers;

import com.RentBikeSystem.JWT_server.Model.IJwtBlock;
import com.RentBikeSystem.JWT_server.Model.JwtBlock;
import org.springframework.stereotype.Service;

import java.util.List;


public interface JwtBlocklistServer {
    public List<JwtBlock> getAllToken();
    public boolean AddToken(String Token,String Email);
    public boolean IsExist(String Token);
    public void DeleteToken(String Token);

}
