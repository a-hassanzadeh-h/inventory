package com.warehouse;

import com.warehouse.app.user.UserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ConfigurationPropertiesScan
public class WarehouseApplication {


	public static void main(String[] args) { SpringApplication.run(WarehouseApplication.class, args); }

	@Bean
	public ApplicationRunner init(UserService userService){
		return args -> userService.populate();
	}
}
