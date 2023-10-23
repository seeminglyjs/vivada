package com.app.vivada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = SecurityAutoConfiguration.class) //스프링 시큐리티 끄는 옵션
public class VivadaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VivadaApplication.class, args);
	}

}
