package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.revature.controller", "com.revature.dao", "com.revature.model","com.revature.service"})
@EntityScan("com.revature.*")

public class MainDriver {

	public static void main(String[] args) {
		SpringApplication.run(MainDriver.class, args);

		//test generating hash
//		String hash = UserService.hashPassword("password");
//		System.out.println(hash);
	}
}