package com.RentBikeSystem.BikeService;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching

/*
* #mvn clean install -D"spring.profiles.active=dev,default"
* */
public class BikeServiceApplication {
	@Bean
	ModelMapper modelMapper(){
		return  new ModelMapper();
	}


	public static void main(String[] args) {
		SpringApplication.run(BikeServiceApplication.class, args);
	}

}
