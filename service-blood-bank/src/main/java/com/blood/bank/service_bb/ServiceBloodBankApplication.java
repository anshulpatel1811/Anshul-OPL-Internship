package com.blood.bank.service_bb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceBloodBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceBloodBankApplication.class, args);
	}

}
