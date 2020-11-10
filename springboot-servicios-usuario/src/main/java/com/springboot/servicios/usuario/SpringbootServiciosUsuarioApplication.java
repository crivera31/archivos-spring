package com.springboot.servicios.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan({"com.sprinboot.servicios.usuario.common.models.entity"})
@SpringBootApplication
@EnableDiscoveryClient
public class SpringbootServiciosUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiciosUsuarioApplication.class, args);
	}
}
