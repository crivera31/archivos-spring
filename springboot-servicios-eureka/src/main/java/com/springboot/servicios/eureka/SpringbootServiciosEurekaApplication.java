package com.springboot.servicios.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringbootServiciosEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiciosEurekaApplication.class, args);
	}

}
