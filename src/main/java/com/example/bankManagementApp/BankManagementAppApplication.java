package com.example.bankManagementApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
@EnableScheduling
public class BankManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankManagementAppApplication.class, args);
	}

}
