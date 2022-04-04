package com.example.warriorRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WarriorRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarriorRegistryApplication.class, args);
	}

}
