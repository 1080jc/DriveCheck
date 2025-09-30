package com.spring.drivechecksi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DrivechecksiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrivechecksiApplication.class, args);
	}

}
