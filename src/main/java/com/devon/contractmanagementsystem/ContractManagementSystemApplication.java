package com.devon.contractmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.devon.contractmanagementsystem.*")
@ComponentScan(basePackages = {"com.devon.contractmanagementsystem.*"})
@EntityScan("com.devon.contractmanagementsystem.*")
public class ContractManagementSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(ContractManagementSystemApplication.class, args);
	}

}
