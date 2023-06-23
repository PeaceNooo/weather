package com.test.JPAdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class JPAdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JPAdemoApplication.class, args);
	}

}
