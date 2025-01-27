package com.example.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@EnableDiscoveryClient
@SpringBootApplication
public class AdminMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminMicroservicesApplication.class, args);
	}

}
