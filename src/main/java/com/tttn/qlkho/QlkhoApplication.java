package com.tttn.qlkho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
// @SpringBootApplication
public class QlkhoApplication {

	public static void main(String[] args) {
		SpringApplication.run(QlkhoApplication.class, args);
	}

}
