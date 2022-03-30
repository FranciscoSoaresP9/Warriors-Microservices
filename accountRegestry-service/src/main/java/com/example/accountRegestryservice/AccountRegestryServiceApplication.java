package com.example.accountRegestryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AccountRegestryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountRegestryServiceApplication.class, args);
	}

}
