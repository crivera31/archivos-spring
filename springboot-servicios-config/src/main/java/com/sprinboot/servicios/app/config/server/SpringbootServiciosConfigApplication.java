package com.sprinboot.servicios.app.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringbootServiciosConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiciosConfigApplication.class, args);
	}

}
