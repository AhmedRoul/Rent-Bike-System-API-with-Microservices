package com.RentBikeSystem.BikeService.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Wellcome {
    @Value("${spring.boot.Message}")
    private  String Message;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<?> responseEntity(){

        return  ResponseEntity.ok(Message);
    }
}
