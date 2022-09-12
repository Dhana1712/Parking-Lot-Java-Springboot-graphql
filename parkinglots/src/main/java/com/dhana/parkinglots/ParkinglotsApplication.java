package com.dhana.parkinglots;

import com.dhana.parkinglots.entity.Role;
import com.dhana.parkinglots.entity.User;
import com.dhana.parkinglots.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ParkinglotsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkinglotsApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

/*	@Bean
	CommandLineRunner run(UserService userService){
		return args-> {
			userService.saveRole(new Role(3, "ROLE_ADMIN"));
			userService.saveRole(new Role(1, "ROLE_PARKING_ATTENDANT"));
			userService.saveRole(new Role(2, "ROLE_USER"));

			userService.saveUser(new User(3, "DHANABAL", "dhana@gmail.com", "12345", new HashSet<>()));
			userService.saveUser(new User(1, "PRAVEEN", "praveen@gmail.com", "1234", new HashSet<>()));
			userService.saveUser(new User(2, "GOKUL", "gokul@gmail.com", "123", new HashSet<>()));

			userService.addRoleToUser("dhana@gmail.com", "ROLE_ADMIN");
			userService.addRoleToUser("dhana@gmail.com", "ROLE_PARKING_ATTENDANT");
			userService.addRoleToUser("praveen@gmail.com", "ROLE_PARKING_ATTENDANT");
			userService.addRoleToUser("gokul@gmail.com", "ROLE_PARKING_ATTENDANT");

		};
	}*/
	/*@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.addRoleToUser("dhana@gmail.com", "ROLE_ADMIN");
		};

	}*/

}
